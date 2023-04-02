package com.maintenecelog.maintenancelog.model;

import lombok.*;
import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String name;

    private String surname;

    private String login;

    private String password;

    private String roles;

    private String email;

    private String phoneNumber;

    private String licenceNumber;


    public User(String name, String surname, String login, String password,  String email, String phoneNumber, String licenceNumber) {
        this.username = login;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.roles = "user";
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.licenceNumber = licenceNumber;
    }
}
