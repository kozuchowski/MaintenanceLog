package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.dto.CreateMachineDto;
import com.maintenecelog.maintenancelog.dto.CreateMainteinerDto;
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

    @Autowired
    public MachineServiceTest(MachineService machineService, MainteinerService mainteinerService) {
        this.machineService = machineService;
        this.mainteinerService = mainteinerService;
    }


    @Test
    void createMachine() {
    }

    @Test
    void shouldReturnMachine() {
        mainteinerService.createMainteiner(
                new CreateMainteinerDto("Test", "test",
                "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                "test@gamil.com","257 35 43 93", "580236776220"));
        Mainteiner mainteiner = mainteinerService.findMainteinerByLogin("test");

        Machine machine = machineService.createMachine(new CreateMachineDto("1234", "12343",
                "34523", LocalDate.of(2000, 01, 01), "faun" , "Test",
                "test@gmail.com", "671 68 5398", "3153635084", mainteiner.getLogin()));

        assertNotNull(machineService.getMachineById(machine.getId()));
        machineService.delete(machine.getId());
        mainteinerService.deleteUserByLogin(mainteiner.getLogin());

    }

    @Test
    void getMachineById() {
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
    void isUnique() {
    }
}