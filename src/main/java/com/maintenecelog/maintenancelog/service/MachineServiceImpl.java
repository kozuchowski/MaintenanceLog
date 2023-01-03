package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.model.Owner;
import com.maintenecelog.maintenancelog.repository.MachineRepository;
import com.maintenecelog.maintenancelog.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class MachineServiceImpl {
    private MachineRepository machineRepository;
    private final OwnerRepository ownerRepository;

    @Autowired
    public MachineServiceImpl(MachineRepository machineRepository,
                              OwnerRepository ownerRepository) {
        this.machineRepository = machineRepository;
        this.ownerRepository = ownerRepository;
    }

    public void addMachine(Machine machine){
        machineRepository.save(machine);
    }
    public Optional<Machine> getMachineById(Long id){
        return machineRepository.findById(id);
    }
    public List<Machine> getAllMachines() {
        return machineRepository.findAll();
    }

    public List<Machine> getAllMachinesForTheMainteiner(Mainteiner mainteiner){
        return   machineRepository.findAllByMainteiner(mainteiner);
    }

    public List<Machine> getAllMachinesForTheOwner(Owner owner){
       return machineRepository.findAllByOwner(owner);
    }

}
