package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.dto.CreateMainteinerDto;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.model.Token;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TokenServiceTest {

    private final TokenService tokenService;
    private final MainteinerService mainteinerService;

    @Autowired
    public TokenServiceTest(TokenService tokenService, MainteinerService mainteinerService) {
        this.tokenService = tokenService;
        this.mainteinerService = mainteinerService;
    }

    @Test
    void shouldReturnToken() {
        CreateMainteinerDto createMainteinerDto = new CreateMainteinerDto("Test", "test",
                "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                "test@gaml.cm","000 00 00 00", "000000000000");
        Token t = mainteinerService.createMainteiner(createMainteinerDto);
        Mainteiner m = mainteinerService.findMainteinerByLogin("test");
        Token token = tokenService.createToken(m);

        assertNotNull(token);

        mainteinerService.deleteUserByLogin("test");
    }

}