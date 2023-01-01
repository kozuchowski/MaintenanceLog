package com.maintenecelog.maintenancelog.controller;

import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.service.MachineService;
import com.maintenecelog.maintenancelog.service.MainteinerCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/machine")
public class MachineController {


    private final MachineService service;
    private final MainteinerCRUDService mainteinerCRUDService;

    @Autowired
    public MachineController(MachineService service, MainteinerCRUDService mainteinerCRUDService) {
        this.service = service;
        this.mainteinerCRUDService = mainteinerCRUDService;
    }

    private Machine machine;
    private Mainteiner mainteiner;



    @GetMapping("/add")
    public void addMachine(@RequestParam String UDT,
                           @RequestParam String VIN,
                           @RequestParam String serial,
                           @RequestParam LocalDate manufactured,
                           @RequestParam LocalDate lastUDTEx,
                           @RequestParam boolean UDTExResult,
                           @RequestParam LocalDate lastMaintenance,
                           @RequestParam boolean maintainerExResult,
                           @RequestParam String manufacturer,
                           @RequestParam String login){





    }
}
