package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.repository.MachineCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MachineService {
    private MachineCRUDRepository repository;

    @Autowired
    public MachineService(MachineCRUDRepository repository) {
        this.repository = repository;
    }

    public void addMachine(Machine machine){
        repository.save(machine);
    }
}
