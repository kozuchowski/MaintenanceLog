package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.exception.ObjectAlreadyExistsException;
import com.maintenecelog.maintenancelog.exception.ObjectDoesNotExistException;
import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.model.Owner;
import com.maintenecelog.maintenancelog.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class MachineServiceImpl implements MachineService{
    private final MachineRepository machineRepository;


    @Autowired
    public MachineServiceImpl(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    public void addMachine(Machine machine){
        machineRepository.save(machine);
    }

    public Machine getMachineById(Long id){
        Machine machine = machineRepository.findById(id).orElseThrow();

        if(machine == null){
            throw new ObjectDoesNotExistException("Machine does not exist in database");
        }
        return machine;
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

    @Override
    public void updateMachine(String UDT,
                              String VIN,
                              String serial,
                              LocalDate manufactured,
                              LocalDate lastUDTEx,
                              boolean UDTExResult,
                              LocalDate lastMaintenance,
                              boolean mainteinerExResult,
                              String manufacturer,
                              Mainteiner mainteiner,
                              Owner owner,
                              Long id) {
        machineRepository.setMachine(UDT, VIN, serial, manufactured, lastUDTEx, UDTExResult, lastMaintenance,
                mainteinerExResult, manufacturer, mainteiner, owner, id);
    }

    @Override
    public void updateMachine(Mainteiner mainteiner, Long id) {
        machineRepository.setMachine(mainteiner, id);
    }
    //No idea what I am doing :) Mindfuck level chess 3d ;D
    @Override
    public boolean isUnique(String UDT, String VIN, String serial) {
        if(machineRepository.findByUnique(UDT, VIN, serial) != null){
            throw new ObjectAlreadyExistsException("Machine already exists in database");
        }
        return true;
    }


}
