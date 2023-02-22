package com.maintenecelog.maintenancelog.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    String ownerName;

    private String ownerEmail;
    private String ownerPhoneNumber;

    private String ownerNIP;


    public Owner(String phoneNumber, String name) {
        this.ownerName = name;
        this.ownerPhoneNumber = phoneNumber;
    }

    public Owner(String ownerName, String email, String phoneNumber, String NIP) {
        this.ownerName = ownerName;
        this.ownerEmail = email;
        this.ownerPhoneNumber = phoneNumber;
        this.ownerNIP = NIP;
    }

}
