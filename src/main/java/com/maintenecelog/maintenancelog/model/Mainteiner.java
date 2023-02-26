package com.maintenecelog.maintenancelog.model;

import lombok.*;
import javax.persistence.*;



//@CustomUnique
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Mainteiner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String surname;

    private String login;

    private String password;

    private String email;
    private String phoneNumber;

    private String licenceNumber;


    public Mainteiner(String name, String surname, String login, String password, String email, String phoneNumber, String licenceNumber) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.licenceNumber = licenceNumber;
    }
}
