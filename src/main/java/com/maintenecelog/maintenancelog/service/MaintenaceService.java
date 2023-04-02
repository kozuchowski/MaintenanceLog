package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.dto.MaintenanceDto;
import com.maintenecelog.maintenancelog.exception.ObjectDoesNotExistException;
import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.Maintenence;
import com.maintenecelog.maintenancelog.repository.MachineRepository;
import com.maintenecelog.maintenancelog.repository.UserRepository;
import com.maintenecelog.maintenancelog.repository.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenaceService {

    private final MaintenanceRepository maintenanceRepository;
    private final MachineRepository machineRepository;
    private final UserRepository userRepository;

    @Autowired
    public MaintenaceService(MaintenanceRepository maintenanceRepository,
                             UserRepository userRepository,
                             MachineRepository machineRepository) {
        this.maintenanceRepository = maintenanceRepository;
        this.userRepository = userRepository;
        this.machineRepository = machineRepository;
    }

    public Maintenence create(MaintenanceDto dto) {

        Optional<Machine> optionalMachine = machineRepository.findById(dto.getMachineId());
        if(optionalMachine.isEmpty()){
            throw new ObjectDoesNotExistException("There is no such machine");
        }
        Machine machine = optionalMachine.get();
        return maintenanceRepository.save(new Maintenence(machine, dto.getDescription(), dto.getExDate()));
    }

    public Maintenence update(MaintenanceDto dto) {
        Optional<Maintenence> optionalMaintenence = maintenanceRepository.findById(dto.getMachineId());
        if(optionalMaintenence.isEmpty()){
            throw new ObjectDoesNotExistException("There is no such maintenence");
        }
        Maintenence maintenence = optionalMaintenence.get();

        maintenence.setId(dto.getMachineId());
        maintenence.setDescription(dto.getDescription());
        maintenence.setExDate(dto.getExDate());
        maintenence.setExResult(dto.isExResult());

        return maintenanceRepository.save(maintenence);
    }

    public void deleteById(Long id) {
        maintenanceRepository.deleteById(id);
    }

    public List<Maintenence> findAllByMachine(Long id){
        return maintenanceRepository.findAllByMachineId(id);
    }

    public Maintenence findByDate(LocalDate exDate) {
        Maintenence maintenence = maintenanceRepository.findByExDate(exDate);

        if(maintenence == null) {
            throw new ObjectDoesNotExistException("No such maintenance");
        }

        return maintenence;
    }

    
}
