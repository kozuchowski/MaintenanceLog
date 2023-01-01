package com.maintenecelog.maintenancelog.controller;

import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.service.MainteinerCRUDService;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
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
    public String createUser(HttpSession session,
                             @RequestParam("name") String name,
                             @RequestParam ("surname") String surname,
                             @RequestParam ("login") String login,
                             @RequestParam ("email") String email,
                             @RequestParam ("licence") String licence,
                             @RequestParam("password") String pass,
                             @RequestParam("confirmPass") String confirmPass){
        mainteiner = new Mainteiner(name, surname, login, pass, email, licence );
        service.createMainteiner(mainteiner);
        session.setAttribute("login", login);
        return name + " " + surname + " " + pass;
    }

    @PostMapping("/logIn")
    public String loginUser(HttpSession session,
                            @RequestParam("login") String login,
                            @RequestParam("password") String pass){
        session.setAttribute("login", login);
        return login + " " + pass;
    }

    @PostMapping("/update")
    public String updateUser(HttpSession session,
                             @RequestParam("name") String name,
                             @RequestParam ("surname") String surname,
                             @RequestParam ("email") String email,
                             @RequestParam ("licence") String licence,
                             @RequestParam("password") String pass,
                             @RequestParam("confirmPass") String confirmPass){
        String login = session.getAttribute("login").toString();
        mainteiner = new Mainteiner(name, surname, login, pass, email, licence );
        service.updateMaintener(mainteiner);
        return login + " " + pass;
    }

    @PostMapping("/delete")
    public String deleteUser(HttpSession session) {
        String login = session.getAttribute("login").toString();
        service.deleteUserByLogin(login);
        return "deleted";
    }
    @PostMapping("/showMaintener")
    public Mainteiner showMaintener(HttpSession session){
        String login = session.getAttribute("login").toString();
        Mainteiner mainteiner = service.findMainteinerByLogin(login);
        return mainteiner;
    }

}
