package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.dto.CreateMachineDto;
import com.maintenecelog.maintenancelog.dto.UpdateMachineDto;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MachineService {
    private final MachineRepository machineRepository;
    private final MainteinerRepository mainteinerRepository;
    private final OwnerRepository ownerRepository;
    private final OwnerService ownerService;

    private final MainteinerService mainteinerService;

    @Autowired
    public MachineService(MachineRepository machineRepository, MainteinerRepository mainteinerRepository, OwnerRepository ownerRepository, OwnerService ownerService, MainteinerService mainteinerService) {
        this.machineRepository = machineRepository;
        this.mainteinerRepository = mainteinerRepository;
        this.ownerRepository = ownerRepository;
        this.ownerService = ownerService;
        this.mainteinerService = mainteinerService;
    }

    public Machine create(CreateMachineDto dto){

        isUnique(dto.getUDTNumber(), dto.getVINNumber(), dto.getSerialNumber());
        Mainteiner mainteiner = mainteinerService.findMainteinerByLogin(dto.getMainteinerLogin());
        Owner owner;
        try {
            owner = ownerService.findOwnerByNIP(dto.getOwnerNIP());
        } catch (ObjectDoesNotExistException e) {
            owner = ownerService.create(new Owner(dto.getOwnerName(), dto.getOwnerEmail(), dto.getOwnerPhoneNumber(),
                    dto.getOwnerNIP()));
        }


        Machine machine = new Machine(dto.getUDTNumber(), dto.getVINNumber(), dto.getSerialNumber(),
                dto.getDateOfManufacture(), dto.getManufacturer(),mainteiner, owner);


        return machineRepository.save(machine);
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

    public List<Machine> getAllMachinesForTheMainteiner(String login){
        Mainteiner mainteiner = mainteinerRepository.findByLogin(login);

        if(mainteiner == null) {
            throw new ObjectDoesNotExistException("No such mainteiner");
        }

        return machineRepository.findAllByMainteinerId(mainteiner.getId());
    }

    public List<Machine> getAllMachinesForTheOwner(Long id){
       ownerService.findOwnerById(id);
       return machineRepository.findAllByOwnerId(id);
    }



    public Machine update(Long id, UpdateMachineDto dto) {
        Optional<Machine> optionalMachine = machineRepository.findById(id);
        if(optionalMachine.isEmpty()) {
            throw new ObjectDoesNotExistException("No such machine");
        }
        Mainteiner mainteiner = mainteinerService.findMainteinerByLogin(dto.getMainteinerLogin());
        Owner owner = ownerService.findOwnerByNIP(dto.getOwnerNIP());

        if(owner == null) {
            throw new ObjectDoesNotExistException("No such owner");
        }

        Machine machine = optionalMachine.get();
        machine.setUDTNumber(dto.getUDTNumber());
        machine.setVINNumber(dto.getVINNumber());
        machine.setSerialNumber(dto.getSerialNumber());
        machine.setDateOfManufacture(dto.getDateOfManufacture());
        machine.setManufacturer(dto.getManufacturer());
        machine.setOwner(owner);
        machine.setMainteiner(mainteiner);

        return machineRepository.save(machine);
    }

    public boolean isUnique(String UDT, String VIN, String serial) {
        if(machineRepository.findByUnique(UDT, VIN, serial) != null){
            throw new ObjectAlreadyExistsException("Machine already exists in database");
        }
        return true;
    }

    @Transactional
    public void delete(Long id) {
        machineRepository.deleteById(id);
    }


}
