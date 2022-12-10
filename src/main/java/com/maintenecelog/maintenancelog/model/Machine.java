package com.maintenecelog.maintenancelog.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Machine {
    private Long id;
    private Long maintenerID;
    private Long ownerId;
    private String UDTNumber;
    private String VINNumber;
    private String serialNumber;
    private LocalDate dateOfManufacture;
    private LocalDate lastUDTexamination;
    private boolean UDTExaminationResult;
    private LocalDate lastMaintenence;
    private List<String> mainteneces;
    private String manufacturer;

    private Owner owner;
}
