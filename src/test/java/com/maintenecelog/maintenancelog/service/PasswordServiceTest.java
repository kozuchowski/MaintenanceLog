package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.dto.CreateMainteinerDto;
import com.maintenecelog.maintenancelog.dto.UpdatePasswordDto;
import com.maintenecelog.maintenancelog.model.User;
import com.maintenecelog.maintenancelog.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PasswordServiceTest {

    private final PasswordService passwordService;
    private final UserService userService;

    private final UserRepository userRepository;

    @Autowired
    public PasswordServiceTest(PasswordService passwordService, UserService userService,
                               UserRepository userRepository) {
        this.passwordService = passwordService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Test
    void shouldUpdatePassword() {
        CreateMainteinerDto createMainteinerDto = new CreateMainteinerDto("Test", "test",
                "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                "test@gaml.cm","000 00 00 00", "000000000000");
        User m = userService.create(createMainteinerDto);

        passwordService.update(new UpdatePasswordDto(m.getId(), m.getPassword(),
                "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0N000", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0N000"));
        User uUser = userRepository.findById(m.getId()).get();

        assertEquals("PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0N000", uUser.getPassword());

        userRepository.deleteById(m.getId());
    }

}