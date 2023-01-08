package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.model.Token;
import com.maintenecelog.maintenancelog.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class TokenServiceImpl implements TokenService{

    private final TokenRepository tokenRepository;
    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    @Autowired
    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }



    public static String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    @Override
    public void deleteToken(Token token) {
        tokenRepository.deleteByToken(token);
    }
    @Override
    public Token getTokenByMaintainer(Mainteiner mainteiner) {
        return tokenRepository.findByMaintainer(mainteiner);
    }

    @Override
    public Token getToken(String token) {
        return tokenRepository.findByToken(token);
    }


    @Override
    public Token createToken(Mainteiner mainteiner) {
        Token token = new Token(generateNewToken(), mainteiner);
        tokenRepository.save(token);
        return token;
    }




}
