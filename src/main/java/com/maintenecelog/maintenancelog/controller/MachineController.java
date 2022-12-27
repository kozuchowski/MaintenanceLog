package com.maintenecelog.maintenancelog.controller;

import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.repository.MainteinerCRUDRepository;
import com.maintenecelog.maintenancelog.service.MachineService;
import com.maintenecelog.maintenancelog.service.MainteinerCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/machine")
public class MachineController {
    @Autowired
    private MainteinerCRUDService mainteinerCRUDService;
    @Autowired
    MachineService machineService;

    private Machine machine;
    private Mainteiner mainteiner;

    @GetMapping("/add")
    public void addMachine(@RequestParam String UDT,
                           @RequestParam String login){

        mainteiner = mainteinerCRUDService.findMainteinerByLogin(login);

        machine = new Machine(UDT, mainteiner);
        machineService.addMachine(machine);



    }
}
