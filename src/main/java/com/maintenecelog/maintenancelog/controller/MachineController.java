package com.maintenecelog.maintenancelog.controller;

import com.maintenecelog.maintenancelog.dto.CreateMachineDto;
import com.maintenecelog.maintenancelog.exception.ObjectAlreadyExistsException;
import com.maintenecelog.maintenancelog.exception.ObjectDoesNotExistException;
import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.service.MachineService;
import com.maintenecelog.maintenancelog.service.MainteinerService;
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


    private final MachineService machineService;
    private final MainteinerService mainteinerService;



    @Autowired
    public MachineController(MachineService machineService, MainteinerService mainteinerService) {
        this.machineService = machineService;
        this.mainteinerService = mainteinerService;


    }
    @PostMapping("/new")
    public void addMachine(@Valid @RequestBody CreateMachineDto dto) {
        machineService.addMachine(dto);
    }


    @GetMapping("/{mainteiner-login}")
    public List<Machine> getMachine(@PathVariable("mainteiner-login") String login){

        Mainteiner mainteiner = mainteinerService.findMainteinerByLogin(login);

        return machineService.getAllMachinesForTheMainteiner(mainteiner);
    }

    @GetMapping("/{id}")
    public Machine getSingleMachine(@PathVariable Long id) {
        return machineService.getMachineById(id);
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
    @ExceptionHandler({ObjectAlreadyExistsException.class, ObjectDoesNotExistException.class})
    public String handleAlreadyExistsExceptions(Exception ex) {

        return ex.getMessage();
    }




}
