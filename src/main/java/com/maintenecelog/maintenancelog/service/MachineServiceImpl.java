package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.dto.CreateMachineDto;
import com.maintenecelog.maintenancelog.exception.ObjectAlreadyExistsException;
import com.maintenecelog.maintenancelog.exception.ObjectDoesNotExistException;
import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.model.Owner;
import com.maintenecelog.maintenancelog.repository.MachineRepository;
import com.maintenecelog.maintenancelog.repository.MainteinerRepository;
import com.maintenecelog.maintenancelog.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MachineServiceImpl{
    private final MachineRepository machineRepository;
    private final MainteinerRepository mainteinerRepository;
    private OwnerRepository ownerRepository;
    private OwnerServiceImpl ownerService;

    @Autowired
    public MachineServiceImpl(MachineRepository machineRepository, MainteinerRepository mainteinerRepository, OwnerRepository ownerRepository, OwnerServiceImpl ownerService) {
        this.machineRepository = machineRepository;
        this.mainteinerRepository = mainteinerRepository;
        this.ownerRepository = ownerRepository;
        this.ownerService = ownerService;
    }

    public void addMachine(CreateMachineDto dto){

        isUnique(dto.getUDTNumber(), dto.getVINNumber(), dto.getSerialNumber());
        Mainteiner mainteiner = mainteinerRepository.findByLogin(dto.getMainteinerLogin());

        ownerService.isUnique(dto.getOwnerEmail(), dto.getOwnerNIP());
        Owner owner = new Owner(dto.getOwnerName(), dto.getOwnerEmail(), dto.getOwnerPhoneNumber(), dto.getOwnerNIP());
        ownerService.addOwner(owner);

        Machine machine = new Machine(dto.getUDTNumber(), dto.getVINNumber(), dto.getSerialNumber(),
                dto.getManufacturer(),mainteiner, owner);


        machineRepository.save(machine);
    }

    public Machine getMachineById(Long id){
        Optional<Machine> machine = machineRepository.findById(id);
        if(!machine.isPresent()){
            throw new ObjectDoesNotExistException("Machine does not exists in database");
        };

        return machine.get();
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



    public void updateMachine(Machine machine) {
        machineRepository.save(machine);
    }

    public boolean isUnique(String UDT, String VIN, String serial) {
        if(machineRepository.findByUnique(UDT, VIN, serial) != null){
            throw new ObjectAlreadyExistsException("Machine already exists in database");
        }
        return true;
    }


}
