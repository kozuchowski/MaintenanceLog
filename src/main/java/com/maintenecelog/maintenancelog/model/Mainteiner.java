package com.maintenecelog.maintenancelog.model;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@ToString
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
    private String licenceNumber;

    @OneToMany(mappedBy="mainteiner")
    @ToString.Exclude
    private List<Machine> machines;

    public Mainteiner(String name, String surname, String login, String password, String email, String licenceNumber) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.email = email;
        this.licenceNumber = licenceNumber;
    }



}
