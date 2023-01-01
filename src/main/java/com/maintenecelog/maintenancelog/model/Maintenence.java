package com.maintenecelog.maintenancelog.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Maintenence {
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="machine_id", nullable=false)
    private Machine machine;
    private String description;
    private LocalDate exDate;

    public Maintenence(Machine machine, String description, LocalDate exDate) {
        this.machine = machine;
        this.description = description;
        this.exDate = exDate;
    }
}
