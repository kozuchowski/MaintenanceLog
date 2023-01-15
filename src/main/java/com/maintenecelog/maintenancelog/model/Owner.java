package com.maintenecelog.maintenancelog.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.pl.NIP;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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

    @NotBlank
    String ownerName;
    @Email
    private String email;
    @NotNull
    @Pattern(regexp = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{3}$", message = "Invalid phone number")
    private String phoneNumber;
    @NotNull
    @Pattern(regexp = "^[0-9]{10}$")
    private String NIP;




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
