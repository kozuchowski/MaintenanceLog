package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.dto.UpdatePasswordDto;
import com.maintenecelog.maintenancelog.exception.PasswordNotValidException;
import com.maintenecelog.maintenancelog.model.User;
import com.maintenecelog.maintenancelog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PasswordService {

    private final UserRepository userRepository;
    private final UserService userService;

    public void update(UpdatePasswordDto dto) {
        User user = userService.getMainteinerByIdI(dto.getMainteinerId());

        if(!user.getPassword().equals(dto.getPassword())) {
            throw new PasswordNotValidException("Password or login not valid");
        }

        if(userService.isPasswordsValid(dto.getNewPassword(), dto.getConfirmPassword(), "Passwords do not match")) {
            user.setPassword(dto.getNewPassword());
        }

        userRepository.save(user);
    }



}
