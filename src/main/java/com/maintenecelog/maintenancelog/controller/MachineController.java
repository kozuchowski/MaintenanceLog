package com.maintenecelog.maintenancelog.controller;

import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.model.Owner;
import com.maintenecelog.maintenancelog.repository.MachineRepository;
import com.maintenecelog.maintenancelog.repository.OwnerRepository;
import com.maintenecelog.maintenancelog.service.MachineServiceImpl;
import com.maintenecelog.maintenancelog.service.MainteinerServiceImpl;
import com.maintenecelog.maintenancelog.service.OwnerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/machine")
public class MachineController {


    private final MachineServiceImpl machineService;
    private final MainteinerServiceImpl mainteinerService;

    private final OwnerServiceImpl ownerService;
    private final OwnerRepository ownerRepository;
    private final MachineRepository machineRepository;

    @Autowired
    public MachineController(MachineServiceImpl machineService, MainteinerServiceImpl mainteinerService,
                             OwnerServiceImpl ownerService,
                             OwnerRepository ownerRepository,
                             MachineRepository machineRepository) {
        this.machineService = machineService;
        this.mainteinerService = mainteinerService;
        this.ownerService = ownerService;
        this.ownerRepository = ownerRepository;
        this.machineRepository = machineRepository;
    }


    @PostMapping("/add/machine")
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
                lastMaintenance, maintainerExResult, manufacturer, mainteiner, owner);


        request.setAttribute("machine", machine.getId());
        machineService.addMachine(machine);

    }

    @GetMapping("/get/machines")
    public List<Machine> getMachine(HttpServletRequest request){
        String login = request.getSession().getAttribute("login").toString();
        Mainteiner mainteiner = mainteinerService.findMainteinerByLogin(login);
        return machineService.getAllMachinesForTheMainteiner(mainteiner);
    }

    @GetMapping("/get/machine/{machineId}")
    public Machine getSingleMachine(@PathVariable Long machineId, HttpServletRequest request) {
        request.getSession().setAttribute("machine", machineId);
        return machineService.getMachineById(machineId).orElseThrow();
    }


}
