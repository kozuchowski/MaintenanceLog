package com.maintenecelog.maintenancelog.controller;

import com.maintenecelog.maintenancelog.dto.CreateMainteinerDto;
import com.maintenecelog.maintenancelog.exception.ObjectAlreadyExistsException;
import com.maintenecelog.maintenancelog.exception.ObjectDoesNotExistException;
import com.maintenecelog.maintenancelog.exception.PasswordNotValidException;
import com.maintenecelog.maintenancelog.model.Token;
import com.maintenecelog.maintenancelog.service.MaintainerServiceImpl;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.service.TokenServiceImpl;
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

    private Mainteiner mainteiner;

    private Token token;
    private final MaintainerServiceImpl mainteinerService;
    private final TokenServiceImpl tokenService;

    @Autowired
    public MainteinerController(MaintainerServiceImpl mainteinerService, TokenServiceImpl tokenService) {
        this.mainteinerService = mainteinerService;
        this.tokenService = tokenService;
    }
    @PostMapping("/new")
    public Token createUser(@Valid @RequestBody CreateMainteinerDto mainteinerDto) {
        return mainteinerService.createMainteiner(mainteinerDto);
    }

    @PostMapping("/")
    public Token loginUser(@RequestParam("login") String login,
                           @RequestParam("password") String pass){

        Mainteiner mainteiner = mainteinerService.findMainteinerByLogin(login);
        mainteinerService.isPasswordsValid(mainteiner.getPassword(), pass, "Password is not valid");

        return tokenService.createToken(mainteiner);


    }

    @PutMapping("/{login}")
    public String updateUser(@Valid @RequestBody Mainteiner mainteiner){

        mainteinerService.updateMaintener(mainteiner);

        return "User updated";
    }


    @DeleteMapping("/{login}")
    public String deleteUser(@PathVariable String login) {

        mainteinerService.deleteUserByLogin(login);

        return "deleted";
    }

    @GetMapping("/{login}")
    public Mainteiner showMainteiner(@PathVariable String login){

        mainteiner = mainteinerService.findMainteinerByLogin(login);

        return mainteiner;
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
