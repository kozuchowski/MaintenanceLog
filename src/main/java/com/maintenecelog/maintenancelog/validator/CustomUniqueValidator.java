package com.maintenecelog.maintenancelog.validator;

import com.maintenecelog.maintenancelog.annotation.CustomUnique;
import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.model.Owner;
import com.maintenecelog.maintenancelog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


@Component
public class CustomUniqueValidator implements ConstraintValidator<CustomUnique, Object> {

    private MaintainerService maintainerService;
    private MachineService machineService;
    private OwnerService ownerService;

    private boolean isUnique = true;

    @Autowired
    public CustomUniqueValidator(MaintainerServiceImpl maintainerService,
                                 MachineServiceImpl machineService,
                                 OwnerServiceImpl ownerService) {

        this.maintainerService = maintainerService;
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
            isUnique = maintainerService.isUnique(((Mainteiner) o).getId(),
                    ((Mainteiner) o).getEmail(), ((Mainteiner) o).getLogin());
        }
        if(o.getClass().equals(Machine.class)){
            isUnique = machineService.isUnique(((Machine) o).getUDTNumber(),
                    ((Machine) o).getVINNumber(), ((Machine) o).getSerialNumber());

        }
        if(o.getClass().equals(Owner.class)){
            isUnique = ownerService.isUnique(((Owner) o).getOwnerEmail(), ((Owner) o).getOwnerNIP());
        }
        System.out.println(isUnique);
        return isUnique;
    }
}
