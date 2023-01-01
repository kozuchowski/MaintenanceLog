package com.maintenecelog.maintenancelog.controller;

import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.model.Owner;
import com.maintenecelog.maintenancelog.service.MachineService;
import com.maintenecelog.maintenancelog.service.MainteinerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@RestController
@RequestMapping("/machine")
public class MachineController {


    private final MachineService machineService;
    private final MainteinerService mainteinerService;

    @Autowired
    public MachineController(MachineService service, MainteinerService mainteinerService) {
        this.machineService = service;
        this.mainteinerService = mainteinerService;
    }


    @GetMapping("/add")
    public void addMachine(HttpSession session,
                           @RequestParam String UDT,
                           @RequestParam String VIN,
                           @RequestParam String serial,
                           @RequestParam LocalDate manufactured,
                           @RequestParam LocalDate lastUDTEx,
                           @RequestParam boolean UDTExResult,
                           @RequestParam LocalDate lastMaintenance,
                           @RequestParam boolean maintainerExResult,
                           @RequestParam String manufacturer) {

        String login = session.getAttribute("login").toString();
        Mainteiner mainteiner = mainteinerService.findMainteinerByLogin(login);
        Owner owner = new Owner();

        Machine machine = new Machine(UDT, VIN, serial, manufactured, lastUDTEx, UDTExResult,
                lastMaintenance, maintainerExResult, manufacturer, mainteiner, owner);

        machineService.addMachine(machine);


    }
}
