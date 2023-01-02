package com.maintenecelog.maintenancelog.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String UDTNumber;
    private String VINNumber;
    private String serialNumber;
    private LocalDate dateOfManufacture;
    private LocalDate lastUDOExamination;
    private boolean UDTExaminationResult;
    private LocalDate lastMaintenance;
    private boolean maintainerExaminationResult;
    private String manufacturer;

    @ManyToOne
    private Mainteiner mainteiner;









    public Machine(String UDTNumber, String VINNumber, String serialNumber, LocalDate dateOfManufacture,
                   LocalDate lastUDOExamination, boolean UDTExaminationResult, LocalDate lastMaintenance,
                   boolean maintainerExaminationResult, String manufacturer, Mainteiner mainteiner) {
        this.UDTNumber = UDTNumber;
        this.VINNumber = VINNumber;
        this.serialNumber = serialNumber;
        this.dateOfManufacture = dateOfManufacture;
        this.lastUDOExamination = lastUDOExamination;
        this.UDTExaminationResult = UDTExaminationResult;
        this.lastMaintenance = lastMaintenance;
        this.maintainerExaminationResult = maintainerExaminationResult;
        this.manufacturer = manufacturer;
        this.mainteiner = mainteiner;

    }
}
