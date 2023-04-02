package com.maintenecelog.maintenancelog.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VersionController {

    @Value("${api.version}")
    String version;

    @GetMapping("/version")
    public String getVersion() {
        return version;
    }
}
