package com.maintenecelog.maintenancelog.controller;

import com.maintenecelog.maintenancelog.service.MainteinerServiceImpl;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class MainteinerController {

    private Mainteiner mainteiner;
    private final MainteinerServiceImpl service;

    @Autowired
    public MainteinerController(MainteinerServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/signIn/mainteiner")
    public String createUser(HttpServletRequest request,
                             @RequestParam("name") String name,
                             @RequestParam ("surname") String surname,
                             @RequestParam ("login") String login,
                             @RequestParam ("email") String email,
                             @RequestParam ("licence") String licence,
                             @RequestParam("password") String pass,
                             @RequestParam("confirmPass") String confirmPass){
        mainteiner = new Mainteiner(name, surname, login, pass, email, licence );
        service.createMainteiner(mainteiner);
        request.getSession().setAttribute("login", login);
        return name + " " + surname + " " + pass;
    }

    @PostMapping("/logIn/mainteiner")
    public String loginUser(HttpServletRequest request,
                            @RequestParam("login") String login,
                            @RequestParam("password") String pass){
        request.getSession().setAttribute("login", login);
        return login + " " + pass;
    }

    @PutMapping("/update/mainteiner")
    public String updateUser(HttpServletRequest request,
                             @RequestParam("name") String name,
                             @RequestParam ("surname") String surname,
                             @RequestParam ("email") String email,
                             @RequestParam ("licence") String licence,
                             @RequestParam("password") String pass,
                             @RequestParam("confirmPass") String confirmPass){
        String login = request.getSession().getAttribute("login").toString();
        mainteiner = new Mainteiner(name, surname, login, pass, email, licence );
        service.updateMaintener(mainteiner);
        return login + " " + pass;
    }

    @DeleteMapping("/delete/mainteiner")
    public String deleteUser(HttpSession session) {
        String login = session.getAttribute("login").toString();
        service.deleteUserByLogin(login);
        return "deleted";
    }
    @GetMapping("/show/mainteiner")
    public Mainteiner showMaintener(HttpServletRequest request){
        String login = request.getSession().getAttribute("login").toString();
        Mainteiner mainteiner = service.findMainteinerByLogin(login);
        return mainteiner;
    }

}
