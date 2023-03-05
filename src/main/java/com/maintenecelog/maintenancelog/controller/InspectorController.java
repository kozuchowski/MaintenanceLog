package com.maintenecelog.maintenancelog.controller;

import com.maintenecelog.maintenancelog.dto.CreateInspectorDto;
import com.maintenecelog.maintenancelog.dto.UpdateInspectorDto;
import com.maintenecelog.maintenancelog.exception.ObjectAlreadyExistsException;
import com.maintenecelog.maintenancelog.exception.ObjectDoesNotExistException;
import com.maintenecelog.maintenancelog.exception.PasswordNotValidException;
import com.maintenecelog.maintenancelog.model.Examinator;
import com.maintenecelog.maintenancelog.service.InspectorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/inspectors")
public class InspectorController {

    private final InspectorsService inspectorsService;

    @Autowired
    public InspectorController(InspectorsService inspectorsService) {
        this.inspectorsService = inspectorsService;
    }

    @PostMapping("/")
    public Examinator create(@Valid @RequestBody CreateInspectorDto dto) {
        return inspectorsService.create(dto);
    }

    @PatchMapping("/")
    public String update(@Valid @RequestBody UpdateInspectorDto dto) {
        inspectorsService.update(dto);

        return "Inspector updated";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        inspectorsService.delete(id);

        return "Inspector deleted";
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
