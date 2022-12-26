package com.maintenecelog.maintenancelog.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Setter
@Getter
@Entity
public class PersonOwner extends Owner {
    private String name;
    private String surname;


}
