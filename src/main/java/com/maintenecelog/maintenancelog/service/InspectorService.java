package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.dto.CreateInspectorDto;
import com.maintenecelog.maintenancelog.dto.UpdateInspectorDto;
import com.maintenecelog.maintenancelog.exception.ObjectDoesNotExistException;
import com.maintenecelog.maintenancelog.model.Inspector;
import com.maintenecelog.maintenancelog.repository.InspectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class InspectorService {
    private final InspectorRepository inspectorRepository;

    @Autowired
    public InspectorService(InspectorRepository inspectorRepository) {
        this.inspectorRepository = inspectorRepository;
    }

    public Inspector create(CreateInspectorDto dto) {
        Inspector inspector;

        if((inspector = inspectorRepository.findByFirstNameAndSurnameAndPhoneNumber(dto.getName(),
                dto.getSurname(), dto.getPhoneNumber())) != null) {
            return inspector;
        }
        inspector = new Inspector(dto.getName(), dto.getSurname(), dto.getPhoneNumber());
        return inspectorRepository.save(inspector);

    }
    public void update(UpdateInspectorDto dto) {
        Optional<Inspector> optionalInspector = inspectorRepository.findById(dto.getId());
        if(optionalInspector.isEmpty()){
            throw new ObjectDoesNotExistException("No such inspector");
        }

        Inspector inspector = optionalInspector.get();
        inspector.setFirstName(dto.getName());
        inspector.setSurname(dto.getSurname());
        inspector.setPhoneNumber(dto.getPhoneNumber());


        inspectorRepository.save(inspector);
    }

    public void delete(Long id) {
        inspectorRepository.deleteById(id);
    }

    public List<Inspector> getAll() {
        return inspectorRepository.findAll();
    }
}
