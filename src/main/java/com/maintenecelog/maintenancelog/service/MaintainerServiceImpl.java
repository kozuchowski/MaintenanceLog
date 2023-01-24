package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.exception.ObjectAlreadyExistsException;
import com.maintenecelog.maintenancelog.exception.ObjectDoesNotExistException;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.repository.MainteinerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@Service
public class MaintainerServiceImpl implements MaintainerService {

    private final MainteinerRepository repository;

    @Autowired
    public MaintainerServiceImpl(MainteinerRepository repository) {
        this.repository = repository;

    }

    @Override
    public void createMainteiner(Mainteiner mainteiner){
        repository.save(mainteiner);
    }

    @Override
    public Mainteiner findMainteinerByLogin(String login){
        Mainteiner mainteiner = repository.findByLogin(login);

        if(mainteiner == null){
            throw new ObjectDoesNotExistException("Mainteiner does not exist in database");
        }
        return mainteiner;
    }
    @Override
    public void deleteUserByLogin(String login){
        repository.deleteByLogin(login);
    }
    @Override
    public void updateMaintener(Mainteiner m){
        repository.setMaintainerByLogin(m.getName(), m.getSurname(), m.getLogin(),
                m.getEmail(), m.getPassword(), m.getLicenceNumber());

    }

    @Override
    public boolean isUnique(String licence, String email, String login) {
        if(repository.findByUnique(licence, email, login) != null){
            throw new ObjectAlreadyExistsException("Mainteiner already exists in database");
        }
        return true;
    }


}
