package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.repository.MainteinerCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainteinerCRUDService {

    private final MainteinerCRUDRepository repository;
    @Autowired
    public MainteinerCRUDService(MainteinerCRUDRepository repository) {
        this.repository = repository;
    }

    public void createMainteiner(Mainteiner mainteiner){
        repository.save(mainteiner);
    }

    public Mainteiner findMainteinerByLogin(String login){
        return repository.findByLogin(login);
    }
    public void deleteUserByLogin(String login){
        repository.deleteByLogin(login);
    }

    public void updateMaintener(Mainteiner m){
        repository.setMaintainerByLogin(m.getName(), m.getSurname(), m.getLogin(), m.getEmail(), m.getPassword(), m.getLicenceNumber());

    }


}
