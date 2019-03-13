package no.oslomet.s315615springdockerclientproject.model;

import lombok.Data;

@Data
public class Building {
    private long id;
    private String name;
    private String address;

    public Building(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Building() {
    }
}
