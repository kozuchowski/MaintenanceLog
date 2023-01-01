package com.maintenecelog.maintenancelog.model;

import lombok.*;

import javax.persistence.Entity;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class PersonOwner extends Owner {
    private String name;
    private String surname;

    public PersonOwner(String name, String surname, String phone) {
        super(phone);
        this.name = name;
        this.surname = surname;
    }
}
