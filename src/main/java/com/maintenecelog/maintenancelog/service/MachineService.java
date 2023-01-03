package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.model.Owner;


import java.util.List;
import java.util.Optional;

public interface MachineService {
    void addMachine(Machine machine);
    Optional<Machine> getMachineById(Long id);
    List<Machine> getAllMachines();

    List<Machine> getAllMachinesForTheMainteiner(Mainteiner mainteiner);

    List<Machine> getAllMachinesForTheOwner(Owner owner);
}
