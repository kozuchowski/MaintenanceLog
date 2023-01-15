package com.maintenecelog.maintenancelog.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @NotNull(message = "Machine is required")
    @ManyToOne
    private Machine machine;
    @NotBlank(message = "Description is required")
    private String description;
    @DateTimeFormat
    private LocalDate exDate;

    public Maintenence(Machine machine, String description, LocalDate exDate) {
        this.machine = machine;
        this.description = description;
        this.exDate = exDate;
    }
}
