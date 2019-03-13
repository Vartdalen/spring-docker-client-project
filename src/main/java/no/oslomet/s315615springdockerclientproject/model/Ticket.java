package no.oslomet.s315615springdockerclientproject.model;

import lombok.Data;

import java.sql.Date;

@Data
public class Ticket {
    private long id;
    private Date date;
    private String film;
    private String cinema;

    private User user;

    public Ticket() {}

    public Ticket(Date date, String film, String cinema, User user) {
        this.date = date;
        this.film = film;
        this.cinema = cinema;
        this.user = user;
    }
}
