package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.dto.ChangePasswordDto;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.repository.MainteinerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PasswordService {

    private final MainteinerRepository mainteinerRepository;
    private final MainteinerService mainteinerService;

    public void update(ChangePasswordDto dto) {
        Mainteiner mainteiner= mainteinerService.getMainteinerByIdIfExist(dto.getMainteinerId());

        if(mainteinerService.isPasswordsValid(dto.getPassword(), dto.getConfirmPassword(), "Passwords do not match")) {
            mainteiner.setPassword(dto.getPassword());
        }

        mainteinerRepository.save(mainteiner);
    }



}
