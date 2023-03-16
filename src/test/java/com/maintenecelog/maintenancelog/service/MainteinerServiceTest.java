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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MainteinerServiceTest {

    private final MainteinerService mainteinerService;
    private final MainteinerRepository mainteinerRepository;

    @Autowired
    public MainteinerServiceTest(MainteinerService mainteinerService, MainteinerRepository mainteinerRepository) {
        this.mainteinerService = mainteinerService;
        this.mainteinerRepository = mainteinerRepository;
    }




    @Test
    void shouldCreateMainteiner() {
        CreateMainteinerDto createMainteinerDto = new CreateMainteinerDto("Test", "test",
                "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                "test@gaml.cm","000 00 00 00", "000000000000");

        Token token = mainteinerService.createMainteiner(createMainteinerDto);
        assertTrue(mainteinerRepository.findById(token.getMainteinerId()).isPresent());

        mainteinerService.deleteUserByLogin("test");

    }


    @Test
    void shouldReturnToken() {
        CreateMainteinerDto createMainteinerDto = new CreateMainteinerDto("Test", "test",
                "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                "test@gaml.cm","000 00 00 00", "000000000000");

        mainteinerService.createMainteiner(createMainteinerDto);
        Token token = mainteinerService.loginUser(new LoginUserDto("test","PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE"));
        assertNotNull(token);
        mainteinerRepository.deleteById(token.getMainteinerId());
    }

    @Test
    void shouldThrowPasswordNotValidException() {
        CreateMainteinerDto createMainteinerDto = new CreateMainteinerDto("Test", "test",
                "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                "test@gaml.cm","000 00 00 00", "000000000000");

        Token token = mainteinerService.createMainteiner(createMainteinerDto);
        assertThrows(PasswordNotValidException.class, () -> {
            mainteinerService.loginUser(new LoginUserDto("test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDA333333"));
        });
        mainteinerRepository.deleteById(token.getMainteinerId());
    }


    @Test
    void shouldReturnMainteiner() {
        CreateMainteinerDto createMainteinerDto = new CreateMainteinerDto("Test", "test",
                "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                "test@gaml.cm","000 00 00 00", "000000000000");

        Token token = mainteinerService.createMainteiner(createMainteinerDto);
        assertNotNull(mainteinerService.findMainteinerByLogin("test"));
        mainteinerRepository.deleteById(token.getMainteinerId());
    }

    @Test
    void shouldThrowObjectDoesNotExistException() {
        assertThrows(ObjectDoesNotExistException.class, () -> {
            mainteinerService.findMainteinerByLogin("test");
        });
    }



    @Test
    void shouldUpdateMainteiner() {
        CreateMainteinerDto createMainteinerDto = new CreateMainteinerDto("Test", "test",
                "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                "test@gaml.cm","000 00 00 00", "000000000000");

        Token token = mainteinerService.createMainteiner(createMainteinerDto);
        UpdateMainteinerDto updateMainteinerDto = new UpdateMainteinerDto("uName", "uSurname", "test",
                "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk",
                "u@gl.cm", "111 11 11 11", "111111111111");

        Mainteiner uM = mainteinerService.updateMaintener(updateMainteinerDto);

        assertEquals("uName", uM.getName());
        assertEquals("uSurname", uM.getSurname());
        assertEquals("test", uM.getLogin());
        assertEquals("PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk", uM.getPassword());
        assertEquals("u@gl.cm", uM.getEmail());
        assertEquals("111 11 11 11", uM.getPhoneNumber());
        assertEquals("111111111111", uM.getLicenceNumber());

        mainteinerRepository.deleteById(token.getMainteinerId());
    }
    @Test
    void shouldReturnTrueIfMainteinerDataIsUnique() {
        assertTrue(mainteinerService.isUnique("000000000000", "1@1.1", "test"));
    }
    @Test
    void shouldThrowObjectAlreadyExistException() {
        CreateMainteinerDto createMainteinerDto = new CreateMainteinerDto("Test", "test",
                "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                "test@gaml.cm","000 00 00 00", "000000000000");

        Token token = mainteinerService.createMainteiner(createMainteinerDto);
        assertThrows(ObjectAlreadyExistsException.class, () -> {
            mainteinerService.isUnique("000000000000", "test@gaml.cm", "test");
        });
        mainteinerRepository.deleteById(token.getMainteinerId());
    }

    @Test
    void shouldDeleteUserByLogin() {
        CreateMainteinerDto createMainteinerDto = new CreateMainteinerDto("Test", "test",
                "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                "test@gaml.cm","000 00 00 00", "000000000000");

        Token token = mainteinerService.createMainteiner(createMainteinerDto);
        mainteinerService.deleteUserByLogin("test");
        assertThrows(NoSuchElementException.class, () -> {
            mainteinerRepository.findById(token.getMainteinerId()).get();
        });
    }

    @Test
    void passwordShouldEqualsConfirm() {
        mainteinerService.isPasswordsValid("test", "test", "message");
    }

    @Test
    void shouldThrowPasswordNotValidException2() {
        assertThrows(PasswordNotValidException.class, () -> {
            mainteinerService.isPasswordsValid("test1", "test2", "message");
        });
    }

//    @Test
//    void dtoIntoMainteiner() {
//    }
//
//    @Test
//    void testDtoIntoMainteiner() {
//    }

    @Test
    void getMainteinerByIdIfExist() {
        CreateMainteinerDto createMainteinerDto = new CreateMainteinerDto("Test", "test",
                "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                "test@gaml.cm","000 00 00 00", "000000000000");

        Token token = mainteinerService.createMainteiner(createMainteinerDto);
        assertThrows(ObjectDoesNotExistException.class, () -> {
            mainteinerService.getMainteinerByIdIfExist(-1l);
        });
        assertNotNull(mainteinerService.getMainteinerByIdIfExist(token.getMainteinerId()));

        mainteinerRepository.deleteById(token.getMainteinerId());
    }
}