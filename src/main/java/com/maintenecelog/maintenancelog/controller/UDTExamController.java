package com.maintenecelog.maintenancelog.controller;

import com.maintenecelog.maintenancelog.dto.UDTExaminationDto;
import com.maintenecelog.maintenancelog.model.UDTExamination;
import com.maintenecelog.maintenancelog.service.UDTExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/udtExams")
public class UDTExamController {

    private final UDTExamService udtExamService;

    @Autowired
    public UDTExamController(UDTExamService udtExamService) {
        this.udtExamService = udtExamService;
    }

    @PostMapping("/")
    public UDTExamination create(UDTExaminationDto dto) {
       return udtExamService.create(dto);
    }

    @PatchMapping("/")
    public String update(@Valid @PathVariable Long id,@Valid @RequestBody UDTExaminationDto dto) {
        udtExamService.update(id, dto);
        return "Examination updated";
    }

    @DeleteMapping("/{id}")
    public String delete(@Valid @PathVariable Long id) {
        udtExamService.delete(id);
        return "Examination deleted";
    }

    @GetMapping("/{id}")
    public UDTExamination showExam(@Valid @PathVariable Long id) {
      return   udtExamService.showUdtExamination(id);
    }

    @GetMapping("/")
    public List<UDTExamination> getAllExams() {
        return udtExamService.showUDTExaminations();
    }

}
