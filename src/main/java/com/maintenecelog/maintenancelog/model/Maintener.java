package com.maintenecelog.maintenancelog.model;

import lombok.Data;

import java.util.List;

@Data
public class Maintener {

    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
    private List<Machine> machines;
}
