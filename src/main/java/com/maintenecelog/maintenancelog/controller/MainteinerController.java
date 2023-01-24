package com.maintenecelog.maintenancelog.controller;

import com.maintenecelog.maintenancelog.exception.ObjectAlreadyExistsException;
import com.maintenecelog.maintenancelog.exception.ObjectDoesNotExistException;
import com.maintenecelog.maintenancelog.model.Token;
import com.maintenecelog.maintenancelog.repository.MachineRepository;
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
    public Token createUser(@Valid @RequestBody Mainteiner mainteiner,
                            @RequestParam("confirmPass") String confirmPass) {
        mainteinerService.isUnique(mainteiner.getLicenceNumber(), mainteiner.getEmail(), mainteiner.getLogin());
        mainteinerService.createMainteiner(mainteiner);
        token = tokenService.createToken(mainteiner);
        return token;
    }


    @PostMapping("/")
    public Token loginUser(@RequestParam("login") String login,
                           @RequestParam("password") String pass){
        Mainteiner mainteiner = mainteinerService.findMainteinerByLogin(login);
        Token token = tokenService.createToken(mainteiner);
        return token;
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
    @ExceptionHandler({ObjectAlreadyExistsException.class})
    public String handleAlreadyExistsExceptions(ObjectAlreadyExistsException ex) {

        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ObjectDoesNotExistException.class})
    public String handleObjectDoesNotExistsExceptions(ObjectDoesNotExistException ex) {

        return ex.getMessage();
    }

}
