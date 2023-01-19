package com.maintenecelog.maintenancelog.model;

import com.maintenecelog.maintenancelog.annotation.CustomUnique;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;


@CustomUnique
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;


    @NotEmpty
    @Column(unique = true)
    @Pattern(regexp="^[0-9]{10}$", message="Invalid UDT number")
    private String UDTNumber;
    @Size(min = 10, max = 17, message = "VIN number must be between 10 and 17 characters long")
    @Column(unique = true)
    @Pattern(regexp="^[a-zA-Z0-9_.-]*$", message="Invalid UDT number")
    private String VINNumber;
    @Column(unique = true)
    @Size(min = 2, max = 32, message = "Serial number number must be between 2 and 32 characters long")
    private String serialNumber;
    @DateTimeFormat
    private LocalDate dateOfManufacture;
    @DateTimeFormat
    private LocalDate lastUDOExamination;
    @NotNull(message = "UDT examination result is required")
    private boolean UDTExaminationResult;
    @DateTimeFormat
    private LocalDate lastMaintenance;
    @NotNull(message = "Mainteiner examination result is required")
    private boolean maintainerExaminationResult;
    @NotBlank(message = "Mabufacturer is required")
    private String manufacturer;

//    @NotNull(message = "Mainteiner is required")
    @ManyToOne
    private Mainteiner mainteiner;

//    @NotNull(message = "Owner is required")
    @ManyToOne
    private Owner owner;


    public Machine(String UDTNumber, String VINNumber, String serialNumber, LocalDate dateOfManufacture,
                   LocalDate lastUDTExamination, boolean UDTExaminationResult, LocalDate lastMaintenance,
                   boolean maintainerExaminationResult, String manufacturer, Mainteiner mainteiner, Owner owner) {
        this.UDTNumber = UDTNumber;
        this.VINNumber = VINNumber;
        this.serialNumber = serialNumber;
        this.dateOfManufacture = dateOfManufacture;
        this.lastUDOExamination = lastUDTExamination;
        this.UDTExaminationResult = UDTExaminationResult;
        this.lastMaintenance = lastMaintenance;
        this.maintainerExaminationResult = maintainerExaminationResult;
        this.manufacturer = manufacturer;
        this.mainteiner = mainteiner;
        this.owner = owner;

    }


}
