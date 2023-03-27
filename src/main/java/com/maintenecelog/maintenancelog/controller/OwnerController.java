package com.maintenecelog.maintenancelog.controller;

import com.maintenecelog.maintenancelog.dto.UpdateOwnerDto;
import com.maintenecelog.maintenancelog.model.Owner;
import com.maintenecelog.maintenancelog.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @PostMapping("/")
    String update(@RequestBody UpdateOwnerDto dto) {
        ownerService.updateOwner(dto);
        return "Owner updated";
    }
}
