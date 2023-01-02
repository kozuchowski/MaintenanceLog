package com.maintenecelog.maintenancelog.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    String ownerName;
    private String email;
    private String phoneNumber;
    private String NIP;

    @OneToMany(cascade= {CascadeType.ALL})
    private List<Machine> machines =
            new ArrayList<>();



    public Owner(String phoneNumber, String name) {
        this.ownerName = name;
        this.phoneNumber = phoneNumber;
    }

    public Owner(String ownerName, String email, String phoneNumber, String NIP) {
        this.ownerName = ownerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.NIP = NIP;
    }
}
