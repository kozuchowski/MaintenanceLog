package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.exception.ObjectAlreadyExistsException;
import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.model.Owner;



import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MachineService {
    void addMachine(Machine machine);
    Machine getMachineById(Long id);
    List<Machine> getAllMachines();

    List<Machine> getAllMachinesForTheMainteiner(Mainteiner mainteiner);

    List<Machine> getAllMachinesForTheOwner(Owner owner);

    void updateMachine (String UDT, String VIN, String serial, LocalDate manufactured, LocalDate lastUDTEx,
                     boolean UDTExResult, LocalDate lastMaintenance, boolean mainteinerExResult,
                     String manufacturer, Mainteiner mainteiner, Owner owner, Long id);

    void updateMachine (Mainteiner mainteiner, Long id);

    boolean isUnique(String UDT, String VIN, String serial);
}
