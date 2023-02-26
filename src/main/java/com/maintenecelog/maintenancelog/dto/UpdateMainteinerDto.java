package com.maintenecelog.maintenancelog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Getter
@Setter
@AllArgsConstructor
public class UpdateMainteinerDto {


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

    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Password minimum eight characters, at least one letter and one number")
    private String confirmPassword;

    @Email
    @Column(unique = true)
    private String email;

    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$",
            message = "Not valid phone number format")
    private String phoneNumber;
    @NotNull
    @Column(unique = true)
    @Pattern(regexp = "^[0-9]{10,12}", message = "Licence number must contains at least 10 and maximum 12 digits")
    private String licenceNumber;
}
