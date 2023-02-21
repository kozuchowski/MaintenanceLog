package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.dto.CreateMainteinerDto;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.model.Token;

public interface MaintainerService {

    Token createMainteiner(CreateMainteinerDto dto);

    Mainteiner findMainteinerByLogin(String login);
    void deleteUserByLogin(String login);

    void updateMaintener(Mainteiner m);


    boolean isUnique(String licence, String email, String login);

    boolean isPasswordsValid(String password, String confirm, String message);

    Mainteiner dtoIntoMainteiner(CreateMainteinerDto createMainteinerDto);


}
