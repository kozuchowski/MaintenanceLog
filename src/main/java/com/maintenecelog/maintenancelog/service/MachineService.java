package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.repository.MachineCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Machine> getMachineById(Long id){
        return repository.findById(id);
    }
    public List<Machine> getAllMachines() {
        return repository.findAll();
    }

    public List<Machine> getAllMachinesForTheMainteiner(Mainteiner mainteiner){
        return   repository.findAllByMainteiner(mainteiner);
    }
}
