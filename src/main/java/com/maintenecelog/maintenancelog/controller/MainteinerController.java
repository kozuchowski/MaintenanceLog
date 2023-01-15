package com.maintenecelog.maintenancelog.controller;

import com.maintenecelog.maintenancelog.model.Token;
import com.maintenecelog.maintenancelog.service.MaintainerServiceImpl;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.service.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Token createUser(@RequestParam("name") String name,
                            @RequestParam ("surname") String surname,
                            @RequestParam ("login") String login,
                            @RequestParam ("email") String email,
                            @RequestParam ("licence") String licence,
                            @RequestParam("password") String pass,
                            @RequestParam("confirmPass") String confirmPass){
        mainteiner = new Mainteiner(name, surname, login, pass, email, licence );
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
    public String updateUser(@PathVariable("login") String login,
                             @RequestParam("name") String name,
                             @RequestParam ("surname") String surname,
                             @RequestParam ("email") String email,
                             @RequestParam ("licence") String licence,
                             @RequestParam("password") String pass,
                             @RequestParam("confirmPass") String confirmPass){

        mainteiner = new Mainteiner(name, surname, login, pass, email, licence );
        mainteinerService.updateMaintener(mainteiner);
        return login + " " + pass;
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
