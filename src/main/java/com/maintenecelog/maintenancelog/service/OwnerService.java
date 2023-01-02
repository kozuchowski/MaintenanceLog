package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.Owner;

public interface OwnerService {

    void addOwner(Owner owner);

    void deleteOwner(Owner owner);

    void updateOwner(Owner owner);

    Owner findOwnerByMachine(Machine machine);
}
