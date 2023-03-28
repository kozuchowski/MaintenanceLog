package com.maintenecelog.maintenancelog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class    UpdateMachineDto {
    @NotEmpty
    @Column(unique = true)
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid UDT number")
    private String UDTNumber;
    @Size(min = 10, max = 17, message = "VIN number must be between 10 and 17 characters long")
    @Column(unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9_.-]*$", message = "Invalid UDT number")
    private String VINNumber;
    @Column(unique = true)
    @Size(min = 2, max = 32, message = "Serial number number must be between 2 and 32 characters long")
    private String serialNumber;
    @DateTimeFormat
    private LocalDate dateOfManufacture;
    @NotBlank(message = "Manufacturer is required")
    private String manufacturer;

    @NotNull(message = "NIP is required")
    @Column(unique = true)
    @Pattern(regexp = "^[0-9]{10}$")
    private String ownerNIP;

    @Column(unique = true)
    @NotBlank(message = "Login is required")
    private String mainteinerLogin;

}