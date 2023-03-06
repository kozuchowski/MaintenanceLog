package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.dto.CreateMachineDto;
import com.maintenecelog.maintenancelog.dto.CreateMainteinerDto;
import com.maintenecelog.maintenancelog.exception.ObjectAlreadyExistsException;
import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class MachineServiceTest {

    private final MachineService machineService;
    private final MainteinerService mainteinerService;

    private final OwnerService ownerService;

    @Autowired
    public MachineServiceTest(MachineService machineService, MainteinerService mainteinerService, OwnerService ownerService) {
        this.machineService = machineService;
        this.mainteinerService = mainteinerService;
        this.ownerService = ownerService;
    }

    @Test
    void shouldCreateMachine() {
        mainteinerService.createMainteiner(
                new CreateMainteinerDto("Test", "test",
                "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                "test@gaml.cm","257 35 43 93", "580236776220"));
        Mainteiner mainteiner = mainteinerService.findMainteinerByLogin("test");

        Machine machine = machineService.createMachine(new CreateMachineDto("1234", "12343",
                "34523", LocalDate.of(2000, 01, 01), "faun" , "Test",
                "test@gmail.com", "671 68 5398", "3153635084", mainteiner.getLogin()));

        assertNotNull(machine);
        assertNotNull(machineService.getMachineById(machine.getId()));


        machineService.delete(machine.getId());
        ownerService.deleteOwner(machine.getOwner());
        mainteinerService.deleteUserByLogin(mainteiner.getLogin());

    }


    @Test
    void getAllMachinesForTheMainteiner() {
    }

    @Test
    void getAllMachinesForTheOwner() {
    }

    @Test
    void updateMachine() {
    }

    @Test
    void shouldThrowObjectAlreadyExistsExcetion() {
        mainteinerService.createMainteiner(
                new CreateMainteinerDto("Test", "test",
                        "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                        "test@gaml.cm","257 35 43 93", "580236776220"));
        Mainteiner mainteiner = mainteinerService.findMainteinerByLogin("test");
        Machine machine = machineService.createMachine(new CreateMachineDto("1234", "12343",
                "34523", LocalDate.of(2000, 01, 01), "faun" , "Test",
                "test@gmail.com", "671 68 5398", "3153635084", mainteiner.getLogin()));

        assertThrows(ObjectAlreadyExistsException.class, () -> {
           machineService.createMachine(new CreateMachineDto("1234", "1234",
                   "3452", LocalDate.of(2000, 01, 01), "faun" , "Test",
                   "test@gmail.com", "671 68 5398", "3153635084", mainteiner.getLogin()));
        });
        assertThrows(ObjectAlreadyExistsException.class, () -> {
            machineService.createMachine(new CreateMachineDto("12342", "12343",
                    "3452", LocalDate.of(2000, 01, 01), "faun" , "Test",
                    "test@gmail.com", "671 68 5398", "3153635084", mainteiner.getLogin()));
        });
        assertThrows(ObjectAlreadyExistsException.class, () -> {
            machineService.createMachine(new CreateMachineDto("12343", "1234",
                    "34523", LocalDate.of(2000, 01, 01), "faun" , "Test",
                    "test@gmail.com", "671 68 5398", "3153635084", mainteiner.getLogin()));
        });

        machineService.delete(machine.getId());
        ownerService.deleteOwner(machine.getOwner());
        mainteinerService.deleteUserByLogin(mainteiner.getLogin());

    }
}