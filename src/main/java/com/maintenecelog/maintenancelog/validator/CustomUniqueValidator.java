package com.maintenecelog.maintenancelog.validator;

import com.maintenecelog.maintenancelog.annotation.CustomUnique;
import com.maintenecelog.maintenancelog.exception.ObjectAlreadyExistsException;
import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.User;
import com.maintenecelog.maintenancelog.model.Owner;
import com.maintenecelog.maintenancelog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;



@Component
public class CustomUniqueValidator implements ConstraintValidator<CustomUnique, Object> {

    private UserService userService;
    private MachineService machineService;
    private OwnerService ownerService;

    private boolean isUnique = true;

    @Autowired
    public CustomUniqueValidator(UserService userService,
                                 MachineService machineService,
                                 OwnerService ownerService) {

        this.userService = userService;
        this.machineService = machineService;
        this.ownerService = ownerService;
    }

    @Override
    public void initialize(CustomUnique constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext ctx) {


        if(o.getClass().equals(User.class)){
            isUnique = userService.isUnique(((User) o).getLicenceNumber(),
                    ((User) o).getEmail(), ((User) o).getLogin());
        }
        if(o.getClass().equals(Machine.class)){
            try {
                isUnique = machineService.isUnique(((Machine) o).getUDTNumber(),
                        ((Machine) o).getVINNumber(), ((Machine) o).getSerialNumber());
            } catch (ObjectAlreadyExistsException e) {
                throw new RuntimeException(e);
            }

        }
        if(o.getClass().equals(Owner.class)){
            isUnique = ownerService.isUnique(((Owner) o).getOwnerEmail(), ((Owner) o).getOwnerNIP());
        }
        System.out.println(isUnique);
        return isUnique;
    }
}
