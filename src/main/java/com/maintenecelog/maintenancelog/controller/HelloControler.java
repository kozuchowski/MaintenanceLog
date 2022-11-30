package com.maintenecelog.maintenancelog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControler {

    @GetMapping("/home")
    public String hello (){
        return "Hello";
    }

}
