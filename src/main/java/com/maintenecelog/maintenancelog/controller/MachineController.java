package com.maintenecelog.maintenancelog.controller;

import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.model.Owner;
import com.maintenecelog.maintenancelog.service.MachineServiceImpl;
import com.maintenecelog.maintenancelog.service.MainteinerServiceImpl;
import com.maintenecelog.maintenancelog.service.OwnerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@RestController
@RequestMapping("/machine")
public class MachineController {


    private final MachineServiceImpl machineService;
    private final MainteinerServiceImpl mainteinerService;

    private final OwnerServiceImpl ownerService;
    @Autowired
    public MachineController(MachineServiceImpl machineService, MainteinerServiceImpl mainteinerService,
                             OwnerServiceImpl ownerService) {
        this.machineService = machineService;
        this.mainteinerService = mainteinerService;
        this.ownerService = ownerService;
    }




    @GetMapping("/add")
    public void addMachine(HttpServletRequest request,
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

        String login = request.getSession().getAttribute("login").toString();
        Mainteiner mainteiner = mainteinerService.findMainteinerByLogin(login);
        Owner owner = new Owner(ownerName, email, phone, NIP);
        ownerService.addOwner(owner);
        Machine machine = new Machine(UDT, VIN, serial, manufactured, lastUDTEx, UDTExResult,
                lastMaintenance, maintainerExResult, manufacturer, mainteiner);

        machineService.addMachine(machine);


    }
}
