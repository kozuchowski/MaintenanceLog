package com.maintenecelog.maintenancelog.model;

import lombok.Data;

import java.util.List;

@Data
public class PersonOwner extends Owner {
    private String name;
    private String surname;


}
