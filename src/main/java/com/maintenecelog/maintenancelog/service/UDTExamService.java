package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.dto.UDTExaminationDto;
import com.maintenecelog.maintenancelog.exception.ObjectDoesNotExistException;
import com.maintenecelog.maintenancelog.model.UDTExamination;
import com.maintenecelog.maintenancelog.repository.UDTExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UDTExamService {


    private final UDTExamRepository uDTExaminationRepository;

    @Autowired
    public UDTExamService(UDTExamRepository uDTExaminationRepository) {
        this.uDTExaminationRepository = uDTExaminationRepository;
    }

    public UDTExamination create(UDTExaminationDto dto) {
        UDTExamination examination = new UDTExamination(dto.getExaminedAt(), dto.isResult(),
                dto.getDescription(), dto.getExaminatorId());

       return uDTExaminationRepository.save(examination);
    }

    public void update(Long id, UDTExaminationDto dto) {
        Optional<UDTExamination> optionalExam = uDTExaminationRepository.findById(id);
        if(optionalExam.isEmpty()) {
            throw new ObjectDoesNotExistException("No such examination");
        }
        UDTExamination examination = optionalExam.get();

        uDTExaminationRepository.save(examination);

    }

    public void delete(Long id) {
        Optional<UDTExamination> examination = uDTExaminationRepository.findById(id);

        if(examination.isEmpty()) {
            throw new ObjectDoesNotExistException("No such examination");
        }
        uDTExaminationRepository.deleteById(id);
    }

    public List<UDTExamination> showUDTExaminations() {
        return uDTExaminationRepository.findAll();
    }

    public UDTExamination showUdtExamination(Long id) {
        Optional<UDTExamination> optionalUDTExamination = uDTExaminationRepository.findById(id);

        if(optionalUDTExamination.isEmpty()) {
            throw new ObjectDoesNotExistException("No scuh examination");
        }
        return optionalUDTExamination.get();

    }
}
