package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.model.Owner;
import com.maintenecelog.maintenancelog.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {
    private final OwnerRepository repository;

    @Autowired
    public OwnerService(OwnerRepository repository) {
        this.repository = repository;
    }

    public void addOwner(Owner owner) {
        repository.save(owner);
    }

    //@TODO Implement methods
    //@TODO Create interfaces for Services
}
