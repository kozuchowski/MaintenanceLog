package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.dto.CreateInspectorDto;
import com.maintenecelog.maintenancelog.dto.CreateMachineDto;
import com.maintenecelog.maintenancelog.dto.CreateMainteinerDto;
import com.maintenecelog.maintenancelog.dto.UDTExaminationDto;
import com.maintenecelog.maintenancelog.model.Inspector;
import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.model.UDTExamination;
import com.maintenecelog.maintenancelog.repository.UDTExamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UDTExamServiceTest {

    private final MainteinerService mainteinerService;
    private final MachineService machineService;
    private final OwnerService ownerService;

    private final UDTExamService udtExamService;
    private final InspectorService inspectorService;
    private final UDTExamRepository udtExamRepository;

    @Autowired
    public UDTExamServiceTest(MainteinerService mainteinerService,
                              MachineService machineService,
                              OwnerService ownerService,
                              UDTExamService udtExamService,
                              InspectorService inspectorService,
                              UDTExamRepository udtExamRepository) {
        this.mainteinerService = mainteinerService;
        this.machineService = machineService;
        this.ownerService = ownerService;
        this.udtExamService = udtExamService;
        this.inspectorService = inspectorService;
        this.udtExamRepository = udtExamRepository;
    }

    @Test
    void create() {
    }
    @Test
    void shouldCreateUDTExam() {
        mainteinerService.createMainteiner(
                new CreateMainteinerDto("Test", "test",
                        "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                        "test@gaml.cm","257 35 43 93", "580236776220"));
        Mainteiner mainteiner = mainteinerService.findMainteinerByLogin("test");

        Machine machine = machineService.createMachine(new CreateMachineDto("1234", "12343",
                "34523", LocalDate.of(2000, 01, 01), "faun" , "Test",
                "test@gmail.com", "671 68 5398", "3153635084", mainteiner.getLogin()));
        CreateInspectorDto inspectorDto = new CreateInspectorDto("test", "test", "000 00 00 00");
        Inspector inspector = inspectorService.create(inspectorDto);
        UDTExaminationDto udtExaminationDto = new UDTExaminationDto(LocalDate.of(2000,01,01), false,
                "test", inspector.getId());
        UDTExamination udtExamination = udtExamService.create(udtExaminationDto);

        assertNotNull(udtExamination);

        inspectorService.delete(inspector.getId());
        udtExamService.delete(udtExamination.getId());
        machineService.delete(machine.getId());
        ownerService.deleteOwner(machine.getOwner());
        mainteinerService.deleteUserByLogin(mainteiner.getLogin());
    }

    @Test
    void shouldUpdateUdtExamination() {
        mainteinerService.createMainteiner(
                new CreateMainteinerDto("Test", "test",
                        "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                        "test@gaml.cm","257 35 43 93", "580236776220"));
        Mainteiner mainteiner = mainteinerService.findMainteinerByLogin("test");

        Machine machine = machineService.createMachine(new CreateMachineDto("1234", "12343",
                "34523", LocalDate.of(2000, 01, 01), "faun" , "Test",
                "test@gmail.com", "671 68 5398", "3153635084", mainteiner.getLogin()));
        CreateInspectorDto inspectorDto = new CreateInspectorDto("test", "test", "000 00 00 00");
        Inspector inspector = inspectorService.create(inspectorDto);
        UDTExaminationDto udtExaminationDto = new UDTExaminationDto(LocalDate.of(2000,01,01), false,
                "test", inspector.getId());
        UDTExamination udtExamination = udtExamService.create(udtExaminationDto);
        udtExamService.update(udtExamination.getId(),
                new UDTExaminationDto(LocalDate.of(2001,01,01), true,
                "uTest", inspector.getId()));

        UDTExamination uUdtExamination = udtExamRepository.findById(udtExamination.getId()).get();

        assertEquals(LocalDate.of(2001,01,01), uUdtExamination.getExaminedAt());
        assertEquals(true, uUdtExamination.isResult());
        assertEquals("uTest", uUdtExamination.getDescription());




        inspectorService.delete(inspector.getId());
        udtExamService.delete(udtExamination.getId());
        machineService.delete(machine.getId());
        ownerService.deleteOwner(machine.getOwner());
        mainteinerService.deleteUserByLogin(mainteiner.getLogin());

    }

    @Test
    void shouldDeleteUdtExamination() {
        mainteinerService.createMainteiner(
                new CreateMainteinerDto("Test", "test",
                        "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                        "test@gaml.cm","257 35 43 93", "580236776220"));
        Mainteiner mainteiner = mainteinerService.findMainteinerByLogin("test");

        Machine machine = machineService.createMachine(new CreateMachineDto("1234", "12343",
                "34523", LocalDate.of(2000, 01, 01), "faun" , "Test",
                "test@gmail.com", "671 68 5398", "3153635084", mainteiner.getLogin()));
        CreateInspectorDto inspectorDto = new CreateInspectorDto("test", "test", "000 00 00 00");
        Inspector inspector = inspectorService.create(inspectorDto);
        UDTExaminationDto udtExaminationDto = new UDTExaminationDto(LocalDate.of(2000,01,01), false,
                "test", inspector.getId());
        UDTExamination udtExamination = udtExamService.create(udtExaminationDto);


        udtExamService.delete(udtExamination.getId());

        assertThrows(NoSuchElementException.class, () -> {
            udtExamRepository.findById(udtExamination.getId()).get();
        });


        inspectorService.delete(inspector.getId());
        machineService.delete(machine.getId());
        ownerService.deleteOwner(machine.getOwner());
        mainteinerService.deleteUserByLogin(mainteiner.getLogin());
    }

    @Test
    void shouldGetUDTExaminations() {
        mainteinerService.createMainteiner(
                new CreateMainteinerDto("Test", "test",
                        "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                        "test@gaml.cm","257 35 43 93", "580236776220"));
        Mainteiner mainteiner = mainteinerService.findMainteinerByLogin("test");

        Machine machine = machineService.createMachine(new CreateMachineDto("1234", "12343",
                "34523", LocalDate.of(2000, 01, 01), "faun" , "Test",
                "test@gmail.com", "671 68 5398", "3153635084", mainteiner.getLogin()));
        CreateInspectorDto inspectorDto = new CreateInspectorDto("test", "test", "000 00 00 00");
        Inspector inspector = inspectorService.create(inspectorDto);
        UDTExaminationDto udtExaminationDto = new UDTExaminationDto(LocalDate.of(2000,01,01), false,
                "test", inspector.getId());
        UDTExamination udtExamination = udtExamService.create(udtExaminationDto);

        List<UDTExamination> exams = udtExamService.showUDTExaminations();

        assertNotNull(exams.get(0));

        inspectorService.delete(inspector.getId());
        udtExamService.delete(udtExamination.getId());
        machineService.delete(machine.getId());
        ownerService.deleteOwner(machine.getOwner());
        mainteinerService.deleteUserByLogin(mainteiner.getLogin());
    }

    @Test
    void shouldGetUdtExamination() {
        mainteinerService.createMainteiner(
                new CreateMainteinerDto("Test", "test",
                        "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                        "test@gaml.cm","257 35 43 93", "580236776220"));
        Mainteiner mainteiner = mainteinerService.findMainteinerByLogin("test");

        Machine machine = machineService.createMachine(new CreateMachineDto("1234", "12343",
                "34523", LocalDate.of(2000, 01, 01), "faun" , "Test",
                "test@gmail.com", "671 68 5398", "3153635084", mainteiner.getLogin()));
        CreateInspectorDto inspectorDto = new CreateInspectorDto("test", "test", "000 00 00 00");
        Inspector inspector = inspectorService.create(inspectorDto);
        UDTExaminationDto udtExaminationDto = new UDTExaminationDto(LocalDate.of(2000,01,01), false,
                "test", inspector.getId());
        UDTExamination udtExamination = udtExamService.create(udtExaminationDto);

        UDTExamination exam = udtExamService.showUdtExamination(udtExamination.getId());

        assertNotNull(exam);

        inspectorService.delete(inspector.getId());
        udtExamService.delete(udtExamination.getId());
        machineService.delete(machine.getId());
        ownerService.deleteOwner(machine.getOwner());
        mainteinerService.deleteUserByLogin(mainteiner.getLogin());

    }
}