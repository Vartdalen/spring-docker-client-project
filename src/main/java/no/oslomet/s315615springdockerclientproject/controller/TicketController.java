package no.oslomet.s315615springdockerclientproject.controller;

import no.oslomet.s315615springdockerclientproject.model.Ticket;
import no.oslomet.s315615springdockerclientproject.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/tickets")
    public String saveTicket(@ModelAttribute("ticket") Ticket newTicket) {
        ticketService.saveTicket(newTicket);
        return "redirect:/";
    }

    @GetMapping("tickets/edit/{id}")
    public String updateTicket(@PathVariable long id, Model model) {
        model.addAttribute("ticket", ticketService.getTicketById(id));
        model.addAttribute("tickets", ticketService.getAllTickets());
        return "index";
    }

    @GetMapping("tickets/delete/{id}")
    public String deleteTicket(@PathVariable long id) {
        ticketService.deleteTicketById(id);
        return "redirect:/";
    }
}
