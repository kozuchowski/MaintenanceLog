package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.model.Token;

public interface MaintainerService {

    void createMainteiner(Mainteiner mainteiner);

    Mainteiner findMainteinerByLogin(String login);
    void deleteUserByLogin(String login);

    void updateMaintener(Mainteiner m);


    boolean isUnique(Long id, String email, String login);
}
