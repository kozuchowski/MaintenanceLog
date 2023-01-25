package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.exception.ObjectAlreadyExistsException;
import com.maintenecelog.maintenancelog.exception.ObjectDoesNotExistException;
import com.maintenecelog.maintenancelog.exception.PasswordNotValidException;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.repository.MainteinerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaintainerServiceImpl implements MaintainerService {

    private final MainteinerRepository mainteinerRepository;

    @Autowired
    public MaintainerServiceImpl(MainteinerRepository repository) {
        this.mainteinerRepository = repository;

    }

    @Override
    public void createMainteiner(Mainteiner mainteiner){
        mainteinerRepository.save(mainteiner);
    }

    @Override
    public Mainteiner findMainteinerByLogin(String login) {
        Mainteiner mainteiner = mainteinerRepository.findByLogin(login);

        if(mainteiner == null){
            throw new ObjectDoesNotExistException("Mainteiner does not exist in database");
        }
        return mainteiner;
    }
    @Override
    public void deleteUserByLogin(String login){
        findMainteinerByLogin(login);
        mainteinerRepository.deleteByLogin(login);
    }

    @Override
    public void updateMaintener(Mainteiner m) {
        findMainteinerByLogin(m.getLogin());
        mainteinerRepository.setMaintainerByLogin(m.getName(), m.getSurname(), m.getLogin(),
                m.getEmail(), m.getPassword(), m.getLicenceNumber());

    }

    @Override
    public boolean isUnique(String licence, String email, String login) {
        if(mainteinerRepository.findByUnique(licence, email, login) != null){
            throw new ObjectAlreadyExistsException("Mainteiner already exists in database");
        }
        return true;
    }

    @Override
    public boolean isPasswordsValid(String password, String confirm, String message) {
        if(!password.equals(confirm)){
            throw new PasswordNotValidException(message);
        }
        return true;
    }




}
