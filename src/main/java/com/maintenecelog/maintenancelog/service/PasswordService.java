package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.dto.UpdatePasswordDto;
import com.maintenecelog.maintenancelog.exception.PasswordNotValidException;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.repository.MainteinerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PasswordService {

    private final MainteinerRepository mainteinerRepository;
    private final MainteinerService mainteinerService;

    public void update(UpdatePasswordDto dto) {
        Mainteiner mainteiner= mainteinerService.getMainteinerByIdI(dto.getMainteinerId());

        if(!mainteiner.getPassword().equals(dto.getPassword())) {
            throw new PasswordNotValidException("Password or login not valid");
        }

        if(mainteinerService.isPasswordsValid(dto.getNewPassword(), dto.getConfirmPassword(), "Passwords do not match")) {
            mainteiner.setPassword(dto.getNewPassword());
        }

        mainteinerRepository.save(mainteiner);
    }



}
