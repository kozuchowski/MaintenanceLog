package com.maintenecelog.maintenancelog.controller;

import com.maintenecelog.maintenancelog.dto.UDTExaminationDto;
import com.maintenecelog.maintenancelog.exception.ObjectAlreadyExistsException;
import com.maintenecelog.maintenancelog.exception.ObjectDoesNotExistException;
import com.maintenecelog.maintenancelog.exception.PasswordNotValidException;
import com.maintenecelog.maintenancelog.model.UDTExamination;
import com.maintenecelog.maintenancelog.service.UDTExamService;
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
@RequestMapping("/udtExams")
public class UDTExamController {

    private final UDTExamService udtExamService;

    @Autowired
    public UDTExamController(UDTExamService udtExamService) {
        this.udtExamService = udtExamService;
    }

    @PostMapping("/")
    public UDTExamination create(UDTExaminationDto dto) {
       return udtExamService.create(dto);
    }

    @PatchMapping("/")
    public String update(@Valid @PathVariable Long id,@Valid @RequestBody UDTExaminationDto dto) {
        udtExamService.update(id, dto);
        return "Examination updated";
    }

    @DeleteMapping("/{id}")
    public String delete(@Valid @PathVariable Long id) {
        udtExamService.delete(id);
        return "Examination deleted";
    }

    @GetMapping("/{id}")
    public UDTExamination showExam(@Valid @PathVariable Long id) {
      return   udtExamService.showUdtExamination(id);
    }

    @GetMapping("/")
    public List<UDTExamination> getAllExams() {
        return udtExamService.showUDTExaminations();
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
