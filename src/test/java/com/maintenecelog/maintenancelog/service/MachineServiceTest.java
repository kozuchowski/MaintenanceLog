package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.dto.CreateMachineDto;
import com.maintenecelog.maintenancelog.dto.CreateMainteinerDto;
import com.maintenecelog.maintenancelog.dto.UpdateMachineDto;
import com.maintenecelog.maintenancelog.exception.ObjectAlreadyExistsException;
import com.maintenecelog.maintenancelog.exception.ObjectDoesNotExistException;
import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.User;
import com.maintenecelog.maintenancelog.model.Owner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class MachineServiceTest {

    private final MachineService machineService;
    private final UserService userService;

    private final OwnerService ownerService;

    @Autowired
    public MachineServiceTest(MachineService machineService, UserService userService, OwnerService ownerService) {
        this.machineService = machineService;
        this.userService = userService;
        this.ownerService = ownerService;
    }

    @Test
    void shouldCreateMachine() {
        userService.create(
                new CreateMainteinerDto("Test", "test",
                "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                "test@gaml.cm","257 35 43 93", "580236776220"));
        User user = userService.findMainteinerByLogin("test");

        Machine machine = machineService.create(new CreateMachineDto("1234", "12343",
                "34523", LocalDate.of(2000, 01, 01), "faun" , "Test",
                "test@gmail.com", "671 68 5398", "3153635084", user.getLogin()));

        assertNotNull(machine);
        assertNotNull(machineService.getMachineById(machine.getId()));


        machineService.delete(machine.getId());
        ownerService.delete(machine.getOwner());
        userService.deleteUserByLogin(user.getLogin());

    }


    @Test
    void shouldThrowObjectDoesNotExistException() {
        assertThrows(ObjectDoesNotExistException.class, () -> {
            machineService.getAllMachinesForTheMainteiner("PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE");
        });

    }

    @Test
    void shouldReturnListOfMachines() {
        userService.create(
                new CreateMainteinerDto("Test", "test",
                        "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                        "test@gaml.cm","000 00 00 00", "580236776220"));
        User user = userService.findMainteinerByLogin("test");
        Machine machine1 = machineService.create(new CreateMachineDto("1234", "12343",
                "34523", LocalDate.of(2000, 01, 01), "faun" , "Test",
                "test@gmail.com", "111 11 1111", "0000000000", user.getLogin()));
        Machine machine2 = machineService.create(new CreateMachineDto("12345", "123432",
                "345234", LocalDate.of(2000, 01, 01), "faun" , "Test",
                "test@gmail.com", "111 11 1111", "0000000000", user.getLogin()));
        Owner owner = machine1.getOwner();

        List<Machine> machines =  machineService.getAllMachinesForTheMainteiner("test");
        List<Machine> machines2 = machineService.getAllMachinesForTheOwner(owner.getId());

        assertNotNull(machines);
        assertNotNull(machines2);

        machineService.delete(machine1.getId());
        machineService.delete(machine2.getId());
        ownerService.delete(machine1.getOwner());
        userService.deleteUserByLogin("test");
    }



    @Test
    void shouldUpdateMachine() {
        userService.create(
                new CreateMainteinerDto("Test", "test",
                        "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                        "test@gaml.cm","000 00 00 00", "000000000000"));
        User user = userService.findMainteinerByLogin("test");
        Machine machine = machineService.create(new CreateMachineDto("1234", "12343",
                "34523", LocalDate.of(2000, 01, 01), "faun" , "Test",
                "test@gmail.com", "111 11 1111", "0000000000", user.getLogin()));

        UpdateMachineDto uDto = new UpdateMachineDto("1111", "11111",
                "111111", LocalDate.of(2001, 01, 01), "Faun" ,
                 "0000000000", user.getLogin());
        machineService.update(machine.getId(), uDto);
        Machine updatedMachine = machineService.getMachineById(machine.getId());


        assertEquals(uDto.getUDTNumber(), updatedMachine.getUDTNumber());
        assertEquals(uDto.getVINNumber(), updatedMachine.getVINNumber());
        assertEquals(uDto.getSerialNumber(), updatedMachine.getSerialNumber());
        assertEquals(uDto.getDateOfManufacture(), updatedMachine.getDateOfManufacture());
        assertEquals(uDto.getManufacturer(), updatedMachine.getManufacturer());
        assertEquals(uDto.getOwnerNIP(), updatedMachine.getOwner().getOwnerNIP());


        machineService.delete(machine.getId());
        ownerService.delete(machine.getOwner());
        userService.deleteUserByLogin("test");

    }

    @Test
    void shouldThrowObjectAlreadyExistsExcetion() {
        userService.create(
                new CreateMainteinerDto("Test", "test",
                        "test", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE", "PtpJtsfeOlcrgKeeJzdRLPaeV5VPDAT0Nk5SE",
                        "test@gaml.cm","000 00 00 00", "000000000000"));
        User user = userService.findMainteinerByLogin("test");
        Machine machine = machineService.create(new CreateMachineDto("1234", "12343",
                "34523", LocalDate.of(2000, 01, 01), "faun" , "Test",
                "test@gmail.com", "111 11 1111", "3153635084", user.getLogin()));

        assertThrows(ObjectAlreadyExistsException.class, () -> {
           machineService.create(new CreateMachineDto("1234", "1234",
                   "3452", LocalDate.of(2000, 01, 01), "faun" , "Test",
                   "test@gmail.com", "671 68 5398", "3153635084", user.getLogin()));
        });
        assertThrows(ObjectAlreadyExistsException.class, () -> {
            machineService.create(new CreateMachineDto("12342", "12343",
                    "3452", LocalDate.of(2000, 01, 01), "faun" , "Test",
                    "test@gmail.com", "671 68 5398", "3153635084", user.getLogin()));
        });
        assertThrows(ObjectAlreadyExistsException.class, () -> {
            machineService.create(new CreateMachineDto("12343", "1234",
                    "34523", LocalDate.of(2000, 01, 01), "faun" , "Test",
                    "test@gmail.com", "671 68 5398", "3153635084", user.getLogin()));
        });

        machineService.delete(machine.getId());
        ownerService.delete(machine.getOwner());
        userService.deleteUserByLogin(user.getLogin());

    }
}