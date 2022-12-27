package com.maintenecelog.maintenancelog.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@RequiredArgsConstructor
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String email;
    private String phoneNumber;
    private String NIP;
//    @OneToMany(mappedBy = "owner")
//    private List<Machine> machines;

//    public void setMachines(Machine machine) {
//        this.machines.add(machine);
//    }
}
