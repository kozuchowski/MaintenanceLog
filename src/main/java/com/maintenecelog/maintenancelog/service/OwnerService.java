package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.exception.ObjectAlreadyExistsException;
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


    public void deleteOwner(Owner owner) {
        repository.delete(owner);
    }


    public void updateOwner(Owner o) {
        repository.updateOwner(o.getOwnerName(), o.getOwnerEmail(), o.getOwnerPhoneNumber(), o.getOwnerNIP(), o.getId());
    }

    public Owner findOwnerById(Long id) {
        return repository.findById(id).orElseThrow();
    }


    public boolean isUnique(String email, String NIP){
        if(repository.findByUnique(email, NIP) != null){
            throw new ObjectAlreadyExistsException("Owner already exists in database");
        }
        return true;
    }

}
