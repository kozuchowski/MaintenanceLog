package com.maintenecelog.maintenancelog.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UDTExamination {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Machine machine;
    private LocalDate examinedAt;
    private boolean result;
    private String description;

    private Long inspectorId;

    public UDTExamination(LocalDate examinedAt, boolean result, String description, Long examinatorId) {
        this.examinedAt = examinedAt;
        this.result = result;
        this.description = description;
        this.inspectorId = examinatorId;
    }
}
