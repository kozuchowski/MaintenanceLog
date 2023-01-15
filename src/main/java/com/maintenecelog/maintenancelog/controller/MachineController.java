package com.maintenecelog.maintenancelog.controller;

import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.model.Owner;
import com.maintenecelog.maintenancelog.service.MachineServiceImpl;
import com.maintenecelog.maintenancelog.service.MaintainerServiceImpl;
import com.maintenecelog.maintenancelog.service.OwnerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/machine")
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


    @PostMapping("/add")
    public void addMachine(@RequestHeader() String auth,
                           @RequestParam String UDT,
                           @RequestParam String VIN,
                           @RequestParam String serial,
                           @RequestParam
                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate manufactured,
                           @RequestParam
                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate lastUDTEx,
                           @RequestParam boolean UDTExResult,
                           @RequestParam
                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate lastMaintenance,
                           @RequestParam boolean maintainerExResult,
                           @RequestParam String manufacturer,
                           @RequestParam String ownerName,
                           @RequestParam String email,
                           @RequestParam String phone,
                           @RequestParam String NIP) {

        Mainteiner mainteiner = mainteinerService.findMaintainerByToken(auth);
        Owner owner = new Owner(ownerName, email, phone, NIP.replaceAll("[^0-9]", ""));
        ownerService.addOwner(owner);
        Machine machine = new Machine(UDT, VIN, serial, manufactured, lastUDTEx, UDTExResult,
                lastMaintenance, maintainerExResult, manufacturer, mainteiner, owner);


        machineService.addMachine(machine);

    }

    @GetMapping("/get")
    public List<Machine> getMachine(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorisation){
        Mainteiner mainteiner = mainteinerService.findMaintainerByToken(authorisation);
        return machineService.getAllMachinesForTheMainteiner(mainteiner);
    }

    @GetMapping("/get/{machineId}")
    public Machine getSingleMachine(@PathVariable Long machineId) {
        return machineService.getMachineById(machineId).orElseThrow();
    }


}
