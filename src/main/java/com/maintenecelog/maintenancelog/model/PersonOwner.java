package com.maintenecelog.maintenancelog.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Setter
@Getter
@Entity
@RequiredArgsConstructor
public class PersonOwner extends Owner {
    private String name;
    private String surname;


}
