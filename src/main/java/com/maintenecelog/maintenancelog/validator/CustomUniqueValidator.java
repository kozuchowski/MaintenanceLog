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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


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
        Field[] fields = ctx.getClass().getFields();
        Map<String, String> fieldsMap = new HashMap<String, String>();
        for(Field f : fields)
            try {
                fieldsMap.put(f.getName(),(String) f.get(o));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        System.out.println(fieldsMap);

        if(ctx.getClass().equals(Mainteiner.class)){
            isUnique = maintainerService.isUnique(Long.valueOf(fieldsMap.get("id")),
                    fieldsMap.get("email"), fieldsMap.get("login"));
        }
        if(ctx.getClass().equals(Machine.class)){
            isUnique = machineService.isUnique(fieldsMap.get("UDTNumber"),
                    fieldsMap.get("VINNumber"), fieldsMap.get("serialNumber"));
        }
        if(ctx.getClass().equals(Owner.class)){
            isUnique = ownerService.isUnique(fieldsMap.get("ownerEmail"), fieldsMap.get("ownerNIP"));
        }

        return isUnique;
    }
}
