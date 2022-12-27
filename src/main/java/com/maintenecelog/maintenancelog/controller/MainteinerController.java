package com.maintenecelog.maintenancelog.controller;

import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.service.MainteinerCRUDService;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class MainteinerController {

    private Mainteiner mainteiner;
    private MainteinerCRUDService service;

    @Autowired
    public MainteinerController(MainteinerCRUDService service) {
        this.service = service;
    }

    @PostMapping("/signIn")
    public String createUser(@RequestParam("name") String name,
                             @RequestParam ("surname") String surname,
                             @RequestParam ("login") String login,
                             @RequestParam ("email") String email,
                             @RequestParam ("licence") String licence,
                             @RequestParam("password") String pass,
                             @RequestParam("confirmPass") String confirmPass){
        mainteiner = new Mainteiner(name, surname, login, pass, email, licence );
        service.createMainteiner(mainteiner);
        return name + " " + surname + " " + pass;
    }

    @PostMapping("/logIn")
    public String loginUser(@RequestParam("login") String login,
                             @RequestParam("password") String pass){
        return login + " " + pass;
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("name") String name,
                             @RequestParam ("surname") String surname,
                             @RequestParam ("login") String login,
                             @RequestParam ("email") String email,
                             @RequestParam ("licence") String licence,
                             @RequestParam("password") String pass,
                             @RequestParam("confirmPass") String confirmPass){
        mainteiner = new Mainteiner(name, surname, login, pass, email, licence );
        service.updateMaintener(mainteiner);
        return login + " " + pass;
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("login") String login,
                            @RequestParam("password") String pass){
        service.deleteUserByLogin(login);
        return "deleted";
    }
    @PostMapping("/showMaintener")
    public Mainteiner showMaintener(@RequestParam("login") String login){
        Mainteiner mainteiner = service.findMainteinerByLogin(login);
        return mainteiner;
    }

}
