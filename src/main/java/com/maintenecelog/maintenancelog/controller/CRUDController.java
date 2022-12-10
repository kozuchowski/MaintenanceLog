package com.maintenecelog.maintenancelog.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class CRUDController {

    @PostMapping("/signIn")
    public String createUser(@RequestParam("name") String name,
                             @RequestParam ("surname") String surname,
                             @RequestParam ("login") String login,
                             @RequestParam ("email") String email,
                             @RequestParam("password") String pass,
                             @RequestParam("confirmPass") String confirmPass){
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

}
