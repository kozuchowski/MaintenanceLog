package com.maintenecelog.maintenancelog.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@AllArgsConstructor
public class UpdateOwnerDto {

    @NotNull
    private Long id;

    @NotBlank(message = "Name is required")
    private String ownerName;

    @Email
    private String ownerEmail;

    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$",
            message = "Not valid phone number format")
    private String ownerPhoneNumber;

    @NotNull
    @Column(unique = true)
    @Pattern(regexp = "^[0-9]{10,12}", message = "NIP number must contains at least 8 and maximum 12 digits")
    private String ownerNIP;
}
