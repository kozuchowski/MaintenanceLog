package com.maintenecelog.maintenancelog.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String email;
    private String phoneNumber;
    private String NIP;
    @OneToMany(mappedBy = "owner")
    private List<Machine> machines;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
