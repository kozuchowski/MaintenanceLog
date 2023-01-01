package com.maintenecelog.maintenancelog.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    public Owner(String phoneNumber, String name) {
        this.ownerName = name;
        this.phoneNumber = phoneNumber;
    }
}
