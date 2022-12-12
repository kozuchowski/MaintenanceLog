package com.maintenecelog.maintenancelog.controller;

import com.maintenecelog.maintenancelog.MainteinerCRUDService;
import com.maintenecelog.maintenancelog.model.Maintener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class CRUDController {

    private Maintener maintener;
    private MainteinerCRUDService service;

    @Autowired
    public CRUDController(MainteinerCRUDService service) {
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
        maintener = new Maintener(name, surname, login, pass, email, licence );
        service.createMainteiner(maintener);
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
                             @RequestParam("password") String pass,
                             @RequestParam("confirmPass") String confirmPass){
        return login + " " + pass;
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("login") String login,
                            @RequestParam("password") String pass){
        return login + " " + pass;
    }
    @PostMapping("/showMaintener")
    public Maintener showMaintener(@RequestParam("id") Long id){
        Optional<Maintener> optionalMainener = service.findMaintenerById(id);
        return optionalMainener.orElse(new Maintener("none", "none", "none", "none", "none", "none"));
    }

}
