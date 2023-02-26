package com.maintenecelog.maintenancelog.controller;

import com.maintenecelog.maintenancelog.dto.CreateMainteinerDto;
import com.maintenecelog.maintenancelog.dto.LoginUserDto;
import com.maintenecelog.maintenancelog.dto.UpdateMainteinerDto;
import com.maintenecelog.maintenancelog.exception.ObjectAlreadyExistsException;
import com.maintenecelog.maintenancelog.exception.ObjectDoesNotExistException;
import com.maintenecelog.maintenancelog.exception.PasswordNotValidException;
import com.maintenecelog.maintenancelog.model.Token;
import com.maintenecelog.maintenancelog.service.MainteinerService;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mainteiners")
public class MainteinerController {

    private final MainteinerService mainteinerService;

    @Autowired
    public MainteinerController(MainteinerService mainteinerService) {
        this.mainteinerService = mainteinerService;

    }
    @PostMapping("/new")
    public Token create(@Valid @RequestBody CreateMainteinerDto mainteinerDto) {
        return mainteinerService.createMainteiner(mainteinerDto);
    }

    @PostMapping("/")
    public Token login(@Valid @RequestBody LoginUserDto dto ){
        return mainteinerService.loginUser(dto);
    }

    @PutMapping("/{login}")
    public String update(@Valid @RequestBody UpdateMainteinerDto dto){

        mainteinerService.updateMaintener(dto);

        return "User updated";
    }


    @DeleteMapping("/{login}")
    public String delete(@PathVariable String login) {

        mainteinerService.deleteUserByLogin(login);

        return "deleted";
    }

    @GetMapping("/{login}")
    public Mainteiner showMainteiner(@PathVariable String login){
        return mainteinerService.findMainteinerByLogin(login);
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
    public String handleAlreadyExistsExceptions(Exception ex) {

        return ex.getMessage();
    }



}
