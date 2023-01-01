package com.maintenecelog.maintenancelog.controller;

import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.service.MachineService;
import com.maintenecelog.maintenancelog.service.MainteinerService;
import com.maintenecelog.maintenancelog.service.OwnerService;
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


    private final MachineService machineService;
    private final MainteinerService mainteinerService;

    private final OwnerService ownerService;
    @Autowired
    public MachineController(MachineService machineService, MainteinerService mainteinerService, OwnerService ownerService) {
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
                           @RequestParam String manufacturer) {

        String login = request.getSession().getAttribute("login").toString();
        Mainteiner mainteiner = mainteinerService.findMainteinerByLogin(login);
        //@TODO Get owner to pass
        Machine machine = new Machine(UDT, VIN, serial, manufactured, lastUDTEx, UDTExResult,
                lastMaintenance, maintainerExResult, manufacturer, mainteiner);

        machineService.addMachine(machine);


    }
}
