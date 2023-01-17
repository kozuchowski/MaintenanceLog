package com.maintenecelog.maintenancelog.controller;

import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.model.Owner;
import com.maintenecelog.maintenancelog.service.MachineServiceImpl;
import com.maintenecelog.maintenancelog.service.MaintainerServiceImpl;
import com.maintenecelog.maintenancelog.service.OwnerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/machines")
public class MachineController {


    private final MachineServiceImpl machineService;
    private final MaintainerServiceImpl mainteinerService;

    private final OwnerServiceImpl ownerService;


    @Autowired
    public MachineController(MachineServiceImpl machineService, MaintainerServiceImpl mainteinerService,
                             OwnerServiceImpl ownerService) {
        this.machineService = machineService;
        this.mainteinerService = mainteinerService;
        this.ownerService = ownerService;

    }
    @PostMapping("/new/{mainteiner-login}")
    public void addMachine(@PathVariable("mainteiner-login") String login,
                           @Valid @RequestBody Machine machine) {

        Mainteiner mainteiner = mainteinerService.findMainteinerByLogin(login);

        Owner owner = machine.getOwner();
        ownerService.addOwner(owner);
        machine.setMainteiner(mainteiner);


        machineService.addMachine(machine);

    }



    @GetMapping("/{mainteiner-login}")
    public List<Machine> getMachine(@PathVariable("mainteiner-login") String login){
        Mainteiner mainteiner = mainteinerService.findMainteinerByLogin(login);
        return machineService.getAllMachinesForTheMainteiner(mainteiner);
    }

    @GetMapping("/{id}")
    public Machine getSingleMachine(@PathVariable Long id) {
        return machineService.getMachineById(id).orElseThrow();
    }


}
