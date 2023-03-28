package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.dto.CreateMainteinerDto;
import com.maintenecelog.maintenancelog.dto.LoginUserDto;
import com.maintenecelog.maintenancelog.dto.UpdateMainteinerDto;
import com.maintenecelog.maintenancelog.exception.ObjectAlreadyExistsException;
import com.maintenecelog.maintenancelog.exception.ObjectDoesNotExistException;
import com.maintenecelog.maintenancelog.exception.PasswordNotValidException;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.model.Token;
import com.maintenecelog.maintenancelog.repository.MainteinerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MainteinerService {

    private final MainteinerRepository mainteinerRepository;

    private final TokenService tokenService;

    @Autowired
    public MainteinerService(MainteinerRepository mainteinerRepository, TokenService tokenService) {
        this.mainteinerRepository = mainteinerRepository;
        this.tokenService = tokenService;
    }



    public Token createMainteiner(CreateMainteinerDto dto){
        isPasswordsValid(dto.getPassword(), dto.getConfirmPassword(),"Passwords don't match");
        isUnique(dto.getLicenceNumber(), dto.getEmail(), dto.getLogin());
        Mainteiner mainteiner = dtoIntoMainteiner(dto);
        mainteinerRepository.save(mainteiner);

        return tokenService.createToken(mainteiner);
    }


    public Token loginUser(LoginUserDto dto) {
        Mainteiner mainteiner = mainteinerRepository.findByLogin(dto.getLogin());
        isPasswordsValid(dto.getPassword(), mainteiner.getPassword(),"Wrong login or password");
        return tokenService.createToken(mainteiner);
    }


    public Mainteiner findMainteinerByLogin(String login) {
        Mainteiner mainteiner = mainteinerRepository.findByLogin(login);

        if(mainteiner == null){
            throw new ObjectDoesNotExistException("Mainteiner does not exist in database");
        }
        return mainteiner;
    }

    @Transactional
    public void deleteUserByLogin(String login){
        findMainteinerByLogin(login);
        mainteinerRepository.deleteByLogin(login);
    }


    public Mainteiner updateMaintener(UpdateMainteinerDto dto) {
        Mainteiner m = mainteinerRepository.findByLogin(dto.getLogin());
        m.setName(dto.getName());
        m.setSurname(dto.getSurname());
        m.setLogin(dto.getLogin());
        if(!(dto.getPassword().equals(dto.getConfirmPassword()))) {
            throw new PasswordNotValidException("Invalid password");
        }
        m.setPassword(dto.getPassword());
        m.setEmail(dto.getEmail());
        m.setPhoneNumber(dto.getPhoneNumber());
        m.setLicenceNumber(dto.getLicenceNumber());

        return mainteinerRepository.save(m);
    }


    public boolean isUnique(String licence, String email, String login) {
        if(mainteinerRepository.findByUnique(licence, email, login) != null){
            throw new ObjectAlreadyExistsException("Mainteiner already exists in database");
        }
        return true;
    }


    public boolean isPasswordsValid(String password, String confirm, String message) {
        if(!password.equals(confirm)){
            throw new PasswordNotValidException(message);
        }
        return true;
    }


    public Mainteiner dtoIntoMainteiner(CreateMainteinerDto dto) {
        return new Mainteiner(dto.getName(), dto.getSurname(), dto.getLogin(), dto.getPassword(), dto.getEmail(),
                dto.getPhoneNumber(), dto.getLicenceNumber());
    }
    public Mainteiner dtoIntoMainteiner(UpdateMainteinerDto dto) {
        return new Mainteiner(dto.getName(), dto.getSurname(), dto.getLogin(), dto.getPassword(), dto.getEmail(),
                dto.getPhoneNumber(), dto.getLicenceNumber());
    }

    public Mainteiner getMainteinerByIdIfExist(Long id) {
        Optional<Mainteiner> optionalMainteiner = mainteinerRepository.findById(id);

        if(optionalMainteiner.isEmpty()) {
            throw new ObjectDoesNotExistException("No such mainteiner");
        }

        return optionalMainteiner.get();

    }


}
