package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.dto.MaintenenceDto;
import com.maintenecelog.maintenancelog.exception.ObjectDoesNotExistException;
import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.Maintenence;
import com.maintenecelog.maintenancelog.repository.MachineRepository;
import com.maintenecelog.maintenancelog.repository.MainteinerRepository;
import com.maintenecelog.maintenancelog.repository.MaintenenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MainteneceService {

    private final MaintenenceRepository maintenenceRepository;
    private final MachineRepository machineRepository;
    private final MainteinerRepository mainteinerRepository;

    @Autowired
    public MainteneceService(MaintenenceRepository maintenenceRepository,
                             MainteinerRepository mainteinerRepository,
                             MachineRepository machineRepository) {
        this.maintenenceRepository = maintenenceRepository;
        this.mainteinerRepository = mainteinerRepository;
        this.machineRepository = machineRepository;
    }

    public void create(MaintenenceDto dto) {

        Optional<Machine> optionalMachine = machineRepository.findById(dto.getMachineId());
        if(optionalMachine.isEmpty()){
            throw new ObjectDoesNotExistException("There is no such machine");
        }
        Machine machine = optionalMachine.get();
        maintenenceRepository.save(new Maintenence(machine, dto.getDescription(), dto.getExDate()));
    }

    public void update(MaintenenceDto dto) {
        Optional<Maintenence> optionalMaintenence = maintenenceRepository.findById(dto.getMachineId());
        if(optionalMaintenence.isEmpty()){
            throw new ObjectDoesNotExistException("There is no such maintenence");
        }
        Maintenence maintenence = optionalMaintenence.get();

        maintenenceRepository.save(maintenence);
    }

    public void deleteById(Long id) {
        maintenenceRepository.deleteById(id);
    }

    public List<Maintenence> findAllByMachine(Long id){
        return maintenenceRepository.findAllByMachineId(id);
    }

    public Maintenence findByDate(LocalDate exDate) {
        return maintenenceRepository.findByExDate(exDate);
    }

    public void deleteByDate(LocalDate date, Machine machine){
        maintenenceRepository.deleteByExDateAndMachine(date, machine);
    }
}
