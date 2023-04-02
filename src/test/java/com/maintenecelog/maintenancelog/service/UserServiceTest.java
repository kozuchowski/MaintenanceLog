package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.dto.CreateMainteinerDto;
import com.maintenecelog.maintenancelog.dto.LoginUserDto;
import com.maintenecelog.maintenancelog.dto.UpdateMainteinerDto;
import com.maintenecelog.maintenancelog.exception.ObjectAlreadyExistsException;
import com.maintenecelog.maintenancelog.exception.ObjectDoesNotExistException;
import com.maintenecelog.maintenancelog.exception.PasswordNotValidException;
import com.maintenecelog.maintenancelog.model.User;
import com.maintenecelog.maintenancelog.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceTest(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }




    @Test
    void shouldCreateMainteiner() {
        CreateMainteinerDto createMainteinerDto = new CreateMainteinerDto("Test", "test",
                "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                "test@gaml.cm","000 00 00 00", "000000000000");

        User m = userService.create(createMainteinerDto);
        assertTrue(userRepository.findById(m.getId()).isPresent());

        userService.deleteUserByLogin("test");

    }


//    @Test
//    void shouldThrowPasswordNotValidException() {
//        CreateMainteinerDto createMainteinerDto = new CreateMainteinerDto("Test", "test",
//                "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
//                "test@gaml.cm","000 00 00 00", "000000000000");
//
//        User m = userService.create(createMainteinerDto);
//        assertThrows(PasswordNotValidException.class, () -> {
//            userService.loginUser(new LoginUserDto("test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDA333333"));
//        });
//        userRepository.deleteById(m.getId());
//    }


    @Test
    void shouldReturnMainteiner() {
        CreateMainteinerDto createMainteinerDto = new CreateMainteinerDto("Test", "test",
                "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                "test@gaml.cm","000 00 00 00", "000000000000");

        User m = userService.create(createMainteinerDto);
        assertNotNull(userService.findMainteinerByLogin("test"));
        userRepository.deleteById(m.getId());
    }

    @Test
    void shouldThrowObjectDoesNotExistException() {
        assertThrows(ObjectDoesNotExistException.class, () -> {
            userService.findMainteinerByLogin("test");
        });
    }



    @Test
    void shouldUpdateMainteiner() {
        CreateMainteinerDto createMainteinerDto = new CreateMainteinerDto("Test", "test",
                "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                "test@gaml.cm","000 00 00 00", "000000000000");

        User m = userService.create(createMainteinerDto);
        UpdateMainteinerDto updateMainteinerDto = new UpdateMainteinerDto("uName", "uSurname", "test",
                "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk",
                "u@gl.cm", "111 11 11 11", "111111111111");

        User uM = userService.update(updateMainteinerDto);

        assertEquals("uName", uM.getName());
        assertEquals("uSurname", uM.getSurname());
        assertEquals("test", uM.getLogin());
        assertEquals("PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk", uM.getPassword());
        assertEquals("u@gl.cm", uM.getEmail());
        assertEquals("111 11 11 11", uM.getPhoneNumber());
        assertEquals("111111111111", uM.getLicenceNumber());

        userRepository.deleteById(m.getId());
    }
    @Test
    void shouldReturnTrueIfMainteinerDataIsUnique() {
        assertTrue(userService.isUnique("000000000000", "1@1.1", "test"));
    }
    @Test
    void shouldThrowObjectAlreadyExistException() {
        CreateMainteinerDto createMainteinerDto = new CreateMainteinerDto("Test", "test",
                "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                "test@gaml.cm","000 00 00 00", "000000000000");

        User m = userService.create(createMainteinerDto);
        assertThrows(ObjectAlreadyExistsException.class, () -> {
            userService.isUnique("000000000000", "test@gaml.cm", "test");
        });
        userRepository.deleteById(m.getId());
    }

    @Test
    void shouldDeleteUserByLogin() {
        CreateMainteinerDto createMainteinerDto = new CreateMainteinerDto("Test", "test",
                "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                "test@gaml.cm","000 00 00 00", "000000000000");

        User m = userService.create(createMainteinerDto);
        userService.deleteUserByLogin("test");
        assertThrows(NoSuchElementException.class, () -> {
            userRepository.findById(m.getId()).get();
        });
    }

    @Test
    void passwordShouldEqualsConfirm() {
        userService.isPasswordsValid("test", "test", "message");
    }

    @Test
    void shouldThrowPasswordNotValidException2() {
        assertThrows(PasswordNotValidException.class, () -> {
            userService.isPasswordsValid("test1", "test2", "message");
        });
    }


    @Test
    void getMainteinerByIdIfExist() {
        CreateMainteinerDto createMainteinerDto = new CreateMainteinerDto("Test", "test",
                "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                "test@gaml.cm","000 00 00 00", "000000000000");

        User m = userService.create(createMainteinerDto);
        assertThrows(ObjectDoesNotExistException.class, () -> {
            userService.getMainteinerByIdI(-1l);
        });
        assertNotNull(userService.getMainteinerByIdI(m.getId()));

        userRepository.deleteById(m.getId());
    }
}