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

    @PostMapping("/ticket")
    public String saveTicket(@ModelAttribute("ticket") Ticket newTicket) {
        ticketService.saveTicket(newTicket);
        return "redirect:/tickets";
    }

    @GetMapping("ticket/edit/{id}")
    public String updateTicket(@PathVariable long id, Model model) {
        model.addAttribute("ticket", ticketService.getTicketById(id));
        model.addAttribute("tickets", ticketService.getAllTickets());
        return "redirect:/tickets";
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
