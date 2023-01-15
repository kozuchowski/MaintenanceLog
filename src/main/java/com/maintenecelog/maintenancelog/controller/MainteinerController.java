package com.maintenecelog.maintenancelog.controller;

import com.maintenecelog.maintenancelog.model.Token;
import com.maintenecelog.maintenancelog.service.MaintainerServiceImpl;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.service.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
                            @RequestParam("confirmPass") String confirmPass){

        mainteinerService.createMainteiner(mainteiner);
        token = tokenService.createToken(mainteiner);
        return token;
    }


    @PostMapping("/")
    public Token loginUser(@RequestParam("login") String login,
                           @RequestParam("password") String pass){

        mainteiner = mainteinerService.findMainteinerByLogin(login);
        token = tokenService.createToken(mainteiner);
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

}
