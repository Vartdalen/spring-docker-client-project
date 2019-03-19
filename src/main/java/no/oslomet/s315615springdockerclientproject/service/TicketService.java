package no.oslomet.s315615springdockerclientproject.service;

import no.oslomet.s315615springdockerclientproject.model.Ticket;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {

    String BASE_URL = "http://localhost:9090/tickets";
    private RestTemplate restTemplate = new RestTemplate();

    public List<Ticket> getAllTickets() {
       return  Arrays.stream(restTemplate.getForObject(BASE_URL, Ticket[].class)).collect(Collectors.toList());
    }

    public Ticket getTicketById(long id) {
        Ticket ticket = restTemplate.getForObject(BASE_URL+"/"+id, Ticket.class);
        return ticket;
    }

    public Ticket saveTicket(Ticket newTicket) {
        return restTemplate.postForObject(BASE_URL, newTicket, Ticket.class);
    }

    public void deleteTicketById(long id) {
        restTemplate.delete(BASE_URL+"/"+id);
    }
}
