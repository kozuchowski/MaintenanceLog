package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.Maintenence;
import com.maintenecelog.maintenancelog.repository.MaintenenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MainteneceService {

    private final MaintenenceRepository repository;

    @Autowired
    public MainteneceService(MaintenenceRepository repository) {
        this.repository = repository;
    }

    public List<Maintenence> findAllByMachine_Id(Long id){
        return repository.findAllByMachine();
    }

    public Maintenence findByDate(LocalDate exDate) {
        return repository.findByExDate(exDate);
    }

    public void deleteByDate(LocalDate date, Machine machine){
        repository.deleteByDate(date, machine);
    }
}