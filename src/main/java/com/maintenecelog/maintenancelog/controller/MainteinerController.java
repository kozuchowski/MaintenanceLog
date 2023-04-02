package com.maintenecelog.maintenancelog.controller;

import com.maintenecelog.maintenancelog.dto.CreateMainteinerDto;
import com.maintenecelog.maintenancelog.dto.LoginUserDto;
import com.maintenecelog.maintenancelog.dto.UpdateMainteinerDto;
import com.maintenecelog.maintenancelog.exception.ObjectAlreadyExistsException;
import com.maintenecelog.maintenancelog.exception.ObjectDoesNotExistException;
import com.maintenecelog.maintenancelog.exception.PasswordNotValidException;
import com.maintenecelog.maintenancelog.model.User;
import com.maintenecelog.maintenancelog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mainteiners")
public class MainteinerController {

    private final UserService userService;

    @Autowired
    public MainteinerController(UserService userService) {
        this.userService = userService;

    }
    @PostMapping("/new")
    public User create(@Valid @RequestBody CreateMainteinerDto mainteinerDto) {
        return userService.create(mainteinerDto);
    }

    @PostMapping("/")
    public User login(@Valid @RequestBody LoginUserDto dto ){
        return userService.loginUser(dto);
    }

    @PutMapping("/{login}")
    public String update(@Valid @RequestBody UpdateMainteinerDto dto){

        userService.update(dto);

        return "User updated";
    }


    @DeleteMapping("/{login}")
    public String delete(@PathVariable String login) {

        userService.deleteUserByLogin(login);

        return "deleted";
    }

    @GetMapping("/{login}")
    public User showMainteiner(@PathVariable String login){
        return userService.findMainteinerByLogin(login);
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
