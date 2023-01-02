package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.Owner;
import com.maintenecelog.maintenancelog.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImpl implements OwnerService{
    private final OwnerRepository repository;

    @Autowired
    public OwnerServiceImpl(OwnerRepository repository) {
        this.repository = repository;
    }

    public void addOwner(Owner owner) {
        repository.save(owner);
    }

    @Override
    public void deleteOwner(Owner owner) {

    }

    @Override
    public void updateOwner(Owner owner) {

    }

    @Override
    public Owner findOwnerByMachine(Machine machine) {
        return null;
    }

}
