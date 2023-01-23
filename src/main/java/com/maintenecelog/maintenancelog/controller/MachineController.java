package com.maintenecelog.maintenancelog.controller;

import com.maintenecelog.maintenancelog.exception.ObjectAlreadyExistsException;
import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.model.Owner;
import com.maintenecelog.maintenancelog.service.MachineServiceImpl;
import com.maintenecelog.maintenancelog.service.MaintainerServiceImpl;
import com.maintenecelog.maintenancelog.service.OwnerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        machineService.isUnique(machine.getUDTNumber(), machine.getVINNumber(), machine.getSerialNumber());

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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ObjectAlreadyExistsException.class})
    public String handleAlreadyExistsExceptions(ObjectAlreadyExistsException ex) {

        return ex.getMessage();
    }


}
