package com.maintenecelog.maintenancelog.model;

import lombok.Data;

import java.util.List;

@Data
public class Owner {
    private Long id;
    private String email;
    private String phoneNumber;
    private String NIP;
    private List<Machine> machines;
}
