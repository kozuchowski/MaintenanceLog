package com.maintenecelog.maintenancelog.controller;

import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/machine")
public class MachineController {


    private MachineService service;

    @Autowired
    public MachineController(MachineService service) {
        this.service = service;
    }

    private Machine machine;
    private Mainteiner mainteiner;

    @GetMapping("/add")
    public void addMachine(@RequestParam String UDT,
                           @RequestParam String login){

    }
}
