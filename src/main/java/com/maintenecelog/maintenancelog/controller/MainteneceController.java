package com.maintenecelog.maintenancelog.controller;

import com.maintenecelog.maintenancelog.dto.MaintenenceDto;
import com.maintenecelog.maintenancelog.model.Maintenence;
import com.maintenecelog.maintenancelog.service.MainteneceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/mainteneces")
public class MainteneceController {

    private final MainteneceService mainteneceService;

    @Autowired
    public MainteneceController(MainteneceService mainteneceService) {
        this.mainteneceService = mainteneceService;
    }

    @PostMapping("/")
    public void create(@Valid @RequestBody MaintenenceDto dto) {
        mainteneceService.create(dto);
    }

    @PatchMapping("/")
    public void update(@Valid @RequestBody MaintenenceDto dto) {
        mainteneceService.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        mainteneceService.deleteById(id);
    }
    @GetMapping("/{machineId}")
    public List<Maintenence> showAllByMachine(@PathVariable Long machineId) {
        return mainteneceService.findAllByMachine(machineId);
    }
}
