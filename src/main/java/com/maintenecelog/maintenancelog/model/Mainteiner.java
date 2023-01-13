package com.maintenecelog.maintenancelog.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class Mainteiner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String login;
    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Password minimum eight characters, at least one letter and one number")
    private String password;
    @Email
    private String email;
    @NotNull
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
