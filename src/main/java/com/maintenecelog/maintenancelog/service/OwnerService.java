package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.dto.UpdateOwnerDto;
import com.maintenecelog.maintenancelog.exception.ObjectAlreadyExistsException;
import com.maintenecelog.maintenancelog.exception.ObjectDoesNotExistException;
import com.maintenecelog.maintenancelog.model.Owner;
import com.maintenecelog.maintenancelog.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class OwnerService {
    private final OwnerRepository repository;

    @Autowired
    public OwnerService(OwnerRepository repository) {
        this.repository = repository;
    }

    public Owner create(Owner owner) {
        if(!isUnique(owner.getOwnerEmail(), owner.getOwnerNIP())){
            throw new ObjectAlreadyExistsException("Owner already exists");
        }
       return repository.save(owner);
    }


    public void deleteOwner(Owner owner) {
        if(!repository.existsById(owner.getId())){
            throw new ObjectDoesNotExistException("No such owner");
        }
        repository.delete(owner);
    }


    public Owner updateOwner(UpdateOwnerDto dto) {
        Optional<Owner> optionalOwner = repository.findById(dto.getId());

        if(optionalOwner.isEmpty()){
            throw new ObjectDoesNotExistException("No such owner");
        }

        Owner owner = optionalOwner.get();

        owner.setOwnerName(dto.getOwnerName());
        owner.setOwnerEmail(dto.getOwnerEmail());
        owner.setOwnerPhoneNumber(dto.getOwnerPhoneNumber());
        owner.setOwnerNIP(dto.getOwnerNIP());

        return repository.save(owner);
    }

    public Owner findOwnerById(Long id) {
        Optional<Owner> optionalOwner = repository.findById(id);
        if(optionalOwner.isEmpty()) {
            throw new ObjectDoesNotExistException("No such owner");
        }
        return optionalOwner.get();
    }

    public Owner findOwnerByNIP(String nip) {
        Owner owner = null;
        if((owner = repository.findOwnerByOwnerNIP(nip)) == null) {
            throw new ObjectDoesNotExistException("No such owner");
        }
       return owner;
    }


    public boolean isUnique(String email, String NIP){
        if(repository.findByUnique(email, NIP) != null){
            throw new ObjectAlreadyExistsException("Owner already exists in database");
        }
        return true;
    }

}
