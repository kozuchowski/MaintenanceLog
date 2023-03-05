package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.dto.CreateInspectorDto;
import com.maintenecelog.maintenancelog.dto.UpdateInspectorDto;
import com.maintenecelog.maintenancelog.exception.ObjectDoesNotExistException;
import com.maintenecelog.maintenancelog.model.Inspector;
import com.maintenecelog.maintenancelog.repository.InspectorRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class InspectorServiceTest {


    private final InspectorService inspectorService;
    private final InspectorRepository inspectorRepository;
    @Autowired
    public InspectorServiceTest(InspectorService inspectorService, InspectorRepository inspectorRepository) {
        this.inspectorService = inspectorService;
        this.inspectorRepository = inspectorRepository;
    }


    CreateInspectorDto inspectorDto = new CreateInspectorDto("Name", "Surname", "+634938547489");




    @Test
    void shouldReturnInspector() {
        Inspector inspector = inspectorService.create(inspectorDto);
        assertInstanceOf(Inspector.class, inspector);

        inspectorService.delete(inspector.getId());

    }

    @Test
    void shouldCreateInspector() {
        Inspector inspector = inspectorService.create(inspectorDto);
        assertEquals(inspector.getFirstName(), inspectorDto.getName());
        assertEquals(inspector.getSurname(), inspectorDto.getSurname());
        assertEquals(inspector.getPhoneNumber(), inspectorDto.getPhoneNumber());

        inspectorService.delete(inspector.getId());

    }

    @Test
    void shouldThrowObjectDoesNotExistException() {
        UpdateInspectorDto updateInspectorDto = new UpdateInspectorDto(-1l, "updateName",
                "updateSurname", "+634938547488");
        assertThrows(ObjectDoesNotExistException.class, () -> {
            inspectorService.update(updateInspectorDto);
        });
    }

    @Test
    void shouldUpdateInspector() {
        Inspector inspector = inspectorService.create(inspectorDto);

        UpdateInspectorDto updateInspectorDto = new UpdateInspectorDto(inspector.getId(), "updateName",
                "updateSurname", "+634938547488");

        inspectorService.update(updateInspectorDto);
        Inspector updatedInspector = inspectorRepository.findById(inspector.getId()).get();

        assertEquals(updateInspectorDto.getName(), updatedInspector.getFirstName());
        assertEquals(updateInspectorDto.getSurname(), updatedInspector.getSurname());
        assertEquals(updateInspectorDto.getPhoneNumber(), updatedInspector.getPhoneNumber());

        inspectorService.delete(inspector.getId());
    }


    @Test
    void shouldReturnListOfInspectors() {
        List<Inspector> inspectors = inspectorService.getAll();
        assertNotNull(inspectors != null);
    }
}