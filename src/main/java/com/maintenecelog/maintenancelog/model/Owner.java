package com.maintenecelog.maintenancelog.model;

import com.maintenecelog.maintenancelog.annotation.CustomUnique;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@CustomUnique
@Setter
@Getter
@Entity
@NoArgsConstructor
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(unique = true)
    @NotBlank(message = "Owner name is required")
    String ownerName;
    @NotNull(message = "Email is required")
    @Column(unique = true)
    @Email
    private String ownerEmail;
    @NotNull
    @Column(unique = true)
    @Pattern(regexp = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{3}$", message = "Invalid phone number")
    private String ownerPhoneNumber;
    @NotNull(message = "NIP is required")
    @Column(unique = true)
    @Pattern(regexp = "^[0-9]{10}$")
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
