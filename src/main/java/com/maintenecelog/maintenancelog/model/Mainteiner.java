package com.maintenecelog.maintenancelog.model;

import com.maintenecelog.maintenancelog.annotation.CustomUnique;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@CustomUnique
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Mainteiner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Surname is required")
    private String surname;
    @Column(unique = true)
    @NotBlank(message = "Login is required")
    private String login;
    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Password minimum eight characters, at least one letter and one number")
    private String password;
    @Email
    @Column(unique = true)
    private String email;
    @NotNull
    @Column(unique = true)
    @Pattern(regexp = "^[0-9]{10,12}", message = "Licence number must contains at least 10 and maximum 12 digits")
    private String licenceNumber;




    public Mainteiner(String name, String surname, String login, String password, String email, String licenceNumber) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.email = email;
        this.licenceNumber = licenceNumber;
    }


}
