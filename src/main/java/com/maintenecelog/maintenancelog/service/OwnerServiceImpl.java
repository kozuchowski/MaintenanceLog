package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.exception.ObjectAlreadyExistsException;
import com.maintenecelog.maintenancelog.model.Owner;
import com.maintenecelog.maintenancelog.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

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
        repository.delete(owner);
    }

    @Override
    public void updateOwner(Owner o) {
        repository.updateOwner(o.getOwnerName(), o.getOwnerEmail(), o.getOwnerPhoneNumber(), o.getOwnerNIP(), o.getId());
    }

    @Override
    public Owner findOwnerById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public boolean isUnique(String email, String NIP){
        if(repository.findByUnique(email, NIP) != null){
            throw new ObjectAlreadyExistsException("Owner already exists in database");
        }
        return true;
    }

}
