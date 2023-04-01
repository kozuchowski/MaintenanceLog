package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.dto.CreateMainteinerDto;
import com.maintenecelog.maintenancelog.dto.UpdatePasswordDto;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.model.Token;
import com.maintenecelog.maintenancelog.repository.MainteinerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PasswordServiceTest {

    private final PasswordService passwordService;
    private final MainteinerService mainteinerService;

    private final MainteinerRepository mainteinerRepository;

    @Autowired
    public PasswordServiceTest(PasswordService passwordService, MainteinerService mainteinerService,
                               MainteinerRepository mainteinerRepository) {
        this.passwordService = passwordService;
        this.mainteinerService = mainteinerService;
        this.mainteinerRepository = mainteinerRepository;
    }

    @Test
    void shouldUpdatePassword() {
        CreateMainteinerDto createMainteinerDto = new CreateMainteinerDto("Test", "test",
                "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                "test@gaml.cm","000 00 00 00", "000000000000");
        Token token = mainteinerService.create(createMainteinerDto);
        Mainteiner m = mainteinerRepository.findById(token.getMainteinerId()).get();

        passwordService.update(new UpdatePasswordDto(token.getMainteinerId(), m.getPassword(),
                "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0N000", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0N000"));
        Mainteiner uMainteiner = mainteinerRepository.findById(token.getMainteinerId()).get();

        assertEquals("PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0N000", uMainteiner.getPassword());

        mainteinerRepository.deleteById(token.getMainteinerId());
    }

}