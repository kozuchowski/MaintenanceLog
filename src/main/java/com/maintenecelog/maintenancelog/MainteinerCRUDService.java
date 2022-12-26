package com.maintenecelog.maintenancelog;

import com.maintenecelog.maintenancelog.model.Mainteiner;
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

    public void createMainteiner(Mainteiner mainteiner){
        repository.save(mainteiner);
    }

    public Optional<Mainteiner> findMaintenerByLogin(String login){
        return repository.findByLogin(login);
    }
    public void deleteUserByLogin(String login){
        repository.deleteByLogin(login);
    }

    public void updateMaintener(Mainteiner m){
        repository.setMaintenerByLogin(m.getName(), m.getSurname(), m.getLogin(), m.getEmail(), m.getPassword(), m.getLicenceNumber());

    }

}
