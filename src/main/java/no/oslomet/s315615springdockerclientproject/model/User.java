package no.oslomet.s315615springdockerclientproject.model;

import lombok.Data;

@Data
public class User {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;

    public User() {}

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
