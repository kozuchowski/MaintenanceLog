package com.maintenecelog.maintenancelog.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


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
    private LocalDate lastUDTexamination;
    private boolean UDTExaminationResult;
    private LocalDate lastMaintenence;
    private boolean maintenerExaminationResult;
    private String manufacturer;
    @OneToMany(mappedBy = "machine")
    private List<Maintenence> mainteneces;


    @ManyToOne
    @JoinColumn(name="mainteiner_id", nullable=false)
    private Mainteiner mainteiner;

//    @ManyToOne
//    @JoinColumn(name="owner_id", nullable=false)
//    private Owner owner;


    public Machine(String UDTNumber,Mainteiner mainteiner) {
        this.UDTNumber = UDTNumber;
//        this.mainteiner = mainteiner;

    }
}
