package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.dto.CreateInspectorDto;
import com.maintenecelog.maintenancelog.dto.UpdateInspectorDto;
import com.maintenecelog.maintenancelog.exception.ObjectDoesNotExistException;
import com.maintenecelog.maintenancelog.model.Examinator;
import com.maintenecelog.maintenancelog.repository.InspectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class InspectorsService {
    private final InspectorRepository inspectorRepository;

    @Autowired
    public InspectorsService(InspectorRepository inspectorRepository) {
        this.inspectorRepository = inspectorRepository;
    }

    public Examinator create(CreateInspectorDto dto) {
        Examinator examinator;

        if((examinator = inspectorRepository.findByFirstNameAndSurnameAndPhoneNumber(dto.getName(),
                dto.getSurname(), dto.getPhoneNumber())) != null) {
            return examinator;
        }
        examinator = new Examinator(dto.getName(), dto.getSurname(), dto.getPhoneNumber());
        return inspectorRepository.save(examinator);

    }
    public void update(UpdateInspectorDto dto) {
        Optional<Examinator> optionalExaminator = inspectorRepository.findById(dto.getId());
        if(optionalExaminator.isEmpty()){
            throw new ObjectDoesNotExistException("No such inspector");
        }
        inspectorRepository.save(optionalExaminator.get());
    }

    public void delete(Long id) {
        inspectorRepository.deleteById(id);
    }

    public List<Examinator> getAll() {
        return inspectorRepository.findAll();
    }
}
