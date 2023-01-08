package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.model.Token;

public interface TokenService {
    void deleteToken(Token token);
    Token createToken(Mainteiner mainteiner);
    Token findTokenByMaintainer(Mainteiner mainteiner);
}
