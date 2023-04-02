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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    private String UDTNumber;

    private String VINNumber;

    private String serialNumber;

    private LocalDate dateOfManufacture;

    private String manufacturer;

    @ManyToOne
    private User user;


    @ManyToOne
    private Owner owner;


    public Machine(String UDTNumber, String VINNumber, String serialNumber, LocalDate dateOfManufacture,
                   String manufacturer, User user, Owner owner) {
        this.UDTNumber = UDTNumber;
        this.VINNumber = VINNumber;
        this.serialNumber = serialNumber;
        this.dateOfManufacture = dateOfManufacture;
        this.manufacturer = manufacturer;
        this.user = user;
        this.owner = owner;

    }

}
