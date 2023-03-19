package com.maintenecelog.maintenancelog.controller;

import com.maintenecelog.maintenancelog.dto.MaintenanceDto;
import com.maintenecelog.maintenancelog.exception.ObjectAlreadyExistsException;
import com.maintenecelog.maintenancelog.exception.ObjectDoesNotExistException;
import com.maintenecelog.maintenancelog.exception.PasswordNotValidException;
import com.maintenecelog.maintenancelog.model.Maintenence;
import com.maintenecelog.maintenancelog.service.MaintenaceService;
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
@RequestMapping("/mainteneces")
public class MainteneceController {

    private final MaintenaceService maintenaceService;

    @Autowired
    public MainteneceController(MaintenaceService maintenaceService) {
        this.maintenaceService = maintenaceService;
    }

    @PostMapping("/")
    public void create(@Valid @RequestBody MaintenanceDto dto) {
        maintenaceService.create(dto);
    }

    @PatchMapping("/")
    public void update(@Valid @RequestBody MaintenanceDto dto) {
        maintenaceService.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        maintenaceService.deleteById(id);
    }
    @GetMapping("/{machineId}")
    public List<Maintenence> showAllByMachine(@PathVariable Long machineId) {
        return maintenaceService.findAllByMachine(machineId);
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
            ObjectDoesNotExistException.class,
            PasswordNotValidException.class})
    public String handleObjectsExceptions(Exception ex) {

        return ex.getMessage();
    }
}
