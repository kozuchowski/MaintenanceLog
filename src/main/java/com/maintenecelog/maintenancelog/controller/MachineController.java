package com.maintenecelog.maintenancelog.controller;

import com.maintenecelog.maintenancelog.dto.CreateMachineDto;
import com.maintenecelog.maintenancelog.dto.UpdateMachineDto;
import com.maintenecelog.maintenancelog.exception.ObjectAlreadyExistsException;
import com.maintenecelog.maintenancelog.exception.ObjectDoesNotExistException;
import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.service.MachineService;
import com.maintenecelog.maintenancelog.service.UserService;
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
    private final UserService userService;



    @Autowired
    public MachineController(MachineService machineService, UserService userService) {
        this.machineService = machineService;
        this.userService = userService;


    }
    @PostMapping("/")
    public void add(@Valid @RequestBody CreateMachineDto dto) {
        machineService.create(dto);
    }

    @PatchMapping("/{id}")
    public String update(@Valid @PathVariable Long id, UpdateMachineDto dto) {
        machineService.update(id, dto);
        return "Machine updated";
    }


    @GetMapping("/{mainteiner-login}")
    public List<Machine> findAllByMainteiner(@PathVariable("mainteiner-login") String login){

        return machineService.getAllMachinesForTheMainteiner(login);
    }

    @GetMapping("/{owner-id}")
    public List<Machine> findAllByOwner(@PathVariable("owner-id") Long id){

        return machineService.getAllMachinesForTheOwner(id);
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
    @ExceptionHandler({ObjectAlreadyExistsException.class,
            ObjectDoesNotExistException.class})
    public String handleObjectsExceptions(Exception ex) {

        return ex.getMessage();
    }




}
