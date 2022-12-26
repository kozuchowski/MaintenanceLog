package com.maintenecelog.maintenancelog.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
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
    private boolean maintenerExaminationRsult;
    @OneToMany(mappedBy = "machine")
    private List<Maintenence> mainteneces;
    private String manufacturer;

    @ManyToOne
    @JoinColumn(name="mainteiner_id", nullable=false)
    private Mainteiner mainteiner;

    @ManyToOne
    @JoinColumn(name="owner_id", nullable=false)
    private Owner owner;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Machine machine = (Machine) o;
        return id != null && Objects.equals(id, machine.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
