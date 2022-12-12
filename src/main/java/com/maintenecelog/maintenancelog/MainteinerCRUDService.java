package com.maintenecelog.maintenancelog;

import com.maintenecelog.maintenancelog.model.Maintener;
import com.maintenecelog.maintenancelog.repository.MainteinerCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MainteinerCRUDService {
    private final MainteinerCRUDRepository repository;
    @Autowired
    public MainteinerCRUDService(MainteinerCRUDRepository repository) {
        this.repository = repository;
    }

    public void createMainteiner(Maintener maintener){
        repository.save(maintener);
    }

    public Optional findMaintenerById(Long id){
        return repository.findById(id);
    }

}
