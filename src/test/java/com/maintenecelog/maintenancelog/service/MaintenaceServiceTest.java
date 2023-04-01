package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.dto.CreateMachineDto;
import com.maintenecelog.maintenancelog.dto.CreateMainteinerDto;
import com.maintenecelog.maintenancelog.dto.MaintenanceDto;
import com.maintenecelog.maintenancelog.exception.ObjectDoesNotExistException;
import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.model.Maintenence;
import com.maintenecelog.maintenancelog.repository.MaintenanceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MaintenaceServiceTest {

    private final MaintenaceService maintenaceService;
    private final MaintenanceRepository maintenanceRepository;
    private final MachineService machineService;
    private final MainteinerService mainteinerService;
    private final OwnerService ownerService;

    @Autowired
    public MaintenaceServiceTest(MaintenaceService maintenaceService,
                                 MaintenanceRepository maintenanceRepository,
                                 MachineService machineService,
                                 MainteinerService mainteinerService,
                                 OwnerService ownerService) {
        this.maintenaceService = maintenaceService;
        this.maintenanceRepository = maintenanceRepository;
        this.machineService = machineService;
        this.mainteinerService = mainteinerService;
        this.ownerService = ownerService;
    }

    @Test
    void shouldCreateMaintenance() {
        mainteinerService.create(
                new CreateMainteinerDto("Test", "test",
                        "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                        "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                        "1@gl.cm","000 00 00 00", "000000000000"));
        Mainteiner mainteiner = mainteinerService.findMainteinerByLogin("test");

        Machine machine = machineService.create(new CreateMachineDto("1234", "12343",
                "34523", LocalDate.of(2000, 01, 01), "faun" , "Test",
                "2@gl.com", "111 11 1111", "1111111111", mainteiner.getLogin()));
        Maintenence maintenence = maintenaceService.create(new MaintenanceDto(machine.getId(), "test", LocalDate.of(2000, 01, 01), true));


        assertNotNull(maintenanceRepository.findById(maintenence.getId()));

        maintenaceService.deleteById(maintenence.getId());
        machineService.delete(machine.getId());
        ownerService.delete(machine.getOwner());
        mainteinerService.deleteUserByLogin(mainteiner.getLogin());


    }

    @Test
    void shouldUpdateMaintenance() {
        mainteinerService.create(
                new CreateMainteinerDto("Test", "test",
                        "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                        "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                        "1@gl.cm","000 00 00 00", "000000000000"));
        Mainteiner mainteiner = mainteinerService.findMainteinerByLogin("test");

        Machine machine = machineService.create(new CreateMachineDto("1234", "12343",
                "34523", LocalDate.of(2000, 01, 01), "faun" , "Test",
                "2@gl.com", "111 11 1111", "1111111111", mainteiner.getLogin()));
        Maintenence maintenance = maintenaceService.create(
                new MaintenanceDto(machine.getId(), "test", LocalDate.of(2000, 01, 01), true));


        Maintenence uMaintenance = maintenaceService.update(
                new MaintenanceDto(maintenance.getId(), "uDescription", LocalDate.of(2001, 01, 01), false));

        assertEquals("uDescription", uMaintenance.getDescription());
        assertEquals(LocalDate.of(2001, 01, 01), uMaintenance.getExDate());
        assertEquals(false, uMaintenance.isExResult());

        maintenaceService.deleteById(maintenance.getId());
        machineService.delete(machine.getId());
        ownerService.delete(machine.getOwner());
        mainteinerService.deleteUserByLogin(mainteiner.getLogin());


    }

    @Test
    void shouldDeleteMaintenance() {
        mainteinerService.create(
                new CreateMainteinerDto("Test", "test",
                        "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                        "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                        "1@gl.cm","000 00 00 00", "000000000000"));
        Mainteiner mainteiner = mainteinerService.findMainteinerByLogin("test");

        Machine machine = machineService.create(new CreateMachineDto("1234", "12343",
                "34523", LocalDate.of(2000, 01, 01), "faun" , "Test",
                "2@gl.com", "111 11 1111", "1111111111", mainteiner.getLogin()));
        Maintenence maintenance = maintenaceService.create(
                new MaintenanceDto(machine.getId(), "test", LocalDate.of(2000, 01, 01), true));

        maintenaceService.deleteById(maintenance.getId());

        assertThrows(NoSuchElementException.class, () -> {
            maintenanceRepository.findById(maintenance.getId()).get();
        });


        machineService.delete(machine.getId());
        ownerService.delete(machine.getOwner());
        mainteinerService.deleteUserByLogin(mainteiner.getLogin());
    }

    @Test
    void shouldReturnListOfMaintenance() {
        mainteinerService.create(
                new CreateMainteinerDto("Test", "test",
                        "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                        "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                        "1@gl.cm","000 00 00 00", "000000000000"));
        Mainteiner mainteiner = mainteinerService.findMainteinerByLogin("test");

        Machine machine = machineService.create(new CreateMachineDto("1234", "12343",
                "34523", LocalDate.of(2000, 01, 01), "faun" , "Test",
                "2@gl.com", "111 11 1111", "1111111111", mainteiner.getLogin()));
        Maintenence maintenance = maintenaceService.create(
                new MaintenanceDto(machine.getId(), "test", LocalDate.of(2000, 01, 01), true));

        Maintenence maintenance2 = maintenaceService.create(
                new MaintenanceDto(machine.getId(), "test2", LocalDate.of(2001, 01, 01), false));

        List<Maintenence> maintenances = maintenaceService.findAllByMachine(machine.getId());

        assertNotNull(maintenances.get(0));
        assertNotNull(maintenances.get(1));

        maintenaceService.deleteById(maintenance.getId());
        maintenaceService.deleteById(maintenance2.getId());
        machineService.delete(machine.getId());
        ownerService.delete(machine.getOwner());
        mainteinerService.deleteUserByLogin(mainteiner.getLogin());

    }

    @Test
    void shouldFindMaintenanceByDate() {
        mainteinerService.create(
                new CreateMainteinerDto("Test", "test",
                        "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                        "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                        "1@gl.cm","000 00 00 00", "000000000000"));
        Mainteiner mainteiner = mainteinerService.findMainteinerByLogin("test");

        Machine machine = machineService.create(new CreateMachineDto("1234", "12343",
                "34523", LocalDate.of(2000, 01, 01), "faun" , "Test",
                "2@gl.com", "111 11 1111", "1111111111", mainteiner.getLogin()));
        Maintenence maintenance = maintenaceService.create(
                new MaintenanceDto(machine.getId(), "test", LocalDate.of(2000, 01, 01), true));

        assertThrows(ObjectDoesNotExistException.class, () -> {
            maintenaceService.findByDate(LocalDate.of(0000, 01,01));
        });

        assertNotNull(maintenaceService.findByDate(LocalDate.of(2000, 01, 01)));

        maintenaceService.deleteById(maintenance.getId());
        machineService.delete(machine.getId());
        ownerService.delete(machine.getOwner());
        mainteinerService.deleteUserByLogin(mainteiner.getLogin());

    }
}