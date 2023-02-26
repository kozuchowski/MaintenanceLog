package com.maintenecelog.maintenancelog.controller;

import com.maintenecelog.maintenancelog.dto.CreateInspectorDto;
import com.maintenecelog.maintenancelog.dto.UpdateInspectorDto;
import com.maintenecelog.maintenancelog.model.Examinator;
import com.maintenecelog.maintenancelog.service.InspectorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/inspectors")
public class InspectorController {

    private final InspectorsService inspectorsService;

    @Autowired
    public InspectorController(InspectorsService inspectorsService) {
        this.inspectorsService = inspectorsService;
    }

    @PostMapping("/")
    public Examinator create(@Valid @RequestBody CreateInspectorDto dto) {
        return inspectorsService.create(dto);
    }

    @PatchMapping("/")
    public String update(@Valid @RequestBody UpdateInspectorDto dto) {
        inspectorsService.update(dto);

        return "Inspector updated";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        inspectorsService.delete(id);

        return "Inspector deleted";
    }
}
