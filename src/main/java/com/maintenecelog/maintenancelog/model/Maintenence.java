package com.maintenecelog.maintenancelog.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Maintenence {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    private Machine machine;

    private String description;

    private LocalDate exDate;

    private boolean exResult;

    public Maintenence(Machine machine, String description, LocalDate exDate) {
        this.machine = machine;
        this.description = description;
        this.exDate = exDate;
    }
}
