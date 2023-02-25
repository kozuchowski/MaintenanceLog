package com.maintenecelog.maintenancelog.validator;

import com.maintenecelog.maintenancelog.annotation.CustomUnique;
import com.maintenecelog.maintenancelog.exception.ObjectAlreadyExistsException;
import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.model.Owner;
import com.maintenecelog.maintenancelog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;



@Component
public class CustomUniqueValidator implements ConstraintValidator<CustomUnique, Object> {

    private MainteinerService mainteinerService;
    private MachineService machineService;
    private OwnerService ownerService;

    private boolean isUnique = true;

    @Autowired
    public CustomUniqueValidator(MainteinerService mainteinerService,
                                 MachineService machineService,
                                 OwnerService ownerService) {

        this.mainteinerService = mainteinerService;
        this.machineService = machineService;
        this.ownerService = ownerService;
    }

    @Override
    public void initialize(CustomUnique constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext ctx) {


        if(o.getClass().equals(Mainteiner.class)){
            isUnique = mainteinerService.isUnique(((Mainteiner) o).getLicenceNumber(),
                    ((Mainteiner) o).getEmail(), ((Mainteiner) o).getLogin());
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
