package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.repository.MainteinerRepository;
import com.maintenecelog.maintenancelog.repository.TokenRepository;
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
    private final TokenRepository tokenRepository;

    @Autowired
    public MaintainerServiceImpl(MainteinerRepository repository,
                                 TokenRepository tokenRepository) {
        this.repository = repository;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void createMainteiner(Mainteiner mainteiner){
        repository.save(mainteiner);
    }

    @Override
    public Mainteiner findMainteinerByLogin(String login){
        return repository.findByLogin(login);
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
    public Mainteiner findMaintainerByToken(String token) {
        return repository.findByToken(token);
    }




}
