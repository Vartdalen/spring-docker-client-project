package no.oslomet.s315615springdockerclientproject.controller;

import no.oslomet.s315615springdockerclientproject.model.Ticket;
import no.oslomet.s315615springdockerclientproject.model.User;
import no.oslomet.s315615springdockerclientproject.service.TicketService;
import no.oslomet.s315615springdockerclientproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class TicketController {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private UserService userService;

    @GetMapping("/ticket")
    public String newTicket(Model model){
        setUserModel(model, SecurityContextHolder.getContext().getAuthentication(), userService);
        Ticket ticket = new Ticket();
        model.addAttribute("ticket", ticket);
        return "ticket";
    }

    @GetMapping("/tickets")
    public String getTickets(Model model) {
        setUserModel(model, SecurityContextHolder.getContext().getAuthentication(), userService);
        List<Ticket> ticketList = ticketService.getAllTickets();
        model.addAttribute("ticketList", ticketList);
        return "tickets";
    }

    @GetMapping("/mytickets")
    public String getMyTickets(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        setUserModel(model, auth, userService);
        List<Ticket> ticketList = ticketService.getAllTickets();
        List<Ticket> myTicketList = new ArrayList<>();
        for(Ticket ticket : ticketList) {
            try {
                if(ticket.getUser().getEmail().equals(auth.getName())) {
                    myTicketList.add(ticket);
                }
            //if ticket has null user, it is not a bug, but it gives a nullpointer which does not need to be handled
            } catch (NullPointerException e) {}
        }
        model.addAttribute("ticketList", myTicketList);
        return "mytickets";
    }

    @PostMapping("/ticket")
    public String saveTicket(@ModelAttribute("ticket") Ticket newTicket) {
        ticketService.saveTicket(newTicket);
        return "redirect:/tickets";
    }

    @GetMapping("ticket/claim/{id}")
    public String claimTicket (@PathVariable long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByEmail(auth.getName()).get();
        Ticket ticket = ticketService.getTicketById(id);
        ticket.setUser(user);
        ticketService.saveTicket(ticket);
        return "redirect:/tickets";
    }

    @GetMapping("ticket/unclaim/{id}")
    public String unClaimTicket(@PathVariable long id) {
        Ticket ticket = ticketService.getTicketById(id);
        ticket.setUser(null);
        ticketService.saveTicket(ticket);
        return "redirect:/mytickets";
    }

    @GetMapping("ticket/delete/{id}")
    public String deleteTicket(@PathVariable long id) {
        ticketService.deleteTicketById(id);
        return "redirect:/tickets";
    }

    private void setUserModel(Model model, Authentication auth, UserService userService) {
        Optional<User> user = userService.getUserByEmail(auth.getName());
        if(user.isPresent()) {
            model.addAttribute("user", user.get());
        }
    }
}
