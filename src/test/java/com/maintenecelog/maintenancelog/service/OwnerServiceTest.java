package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.dto.UpdateOwnerDto;
import com.maintenecelog.maintenancelog.exception.ObjectAlreadyExistsException;
import com.maintenecelog.maintenancelog.exception.ObjectDoesNotExistException;
import com.maintenecelog.maintenancelog.model.Owner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OwnerServiceTest {


    private final OwnerService ownerService;

    @Autowired
    public OwnerServiceTest(OwnerService ownerService) {
        this.ownerService = ownerService;

    }

    @Test
    void addOwner() {
    }

    @Test
    void shouldCreateOwner() {

        Owner owner = ownerService.create(new Owner("test", "te@gm.c", "000 00 00 00", "000000000000"));

        assertNotNull(owner);
        assertThrows(ObjectAlreadyExistsException.class, () -> {
            ownerService.create(owner);
        });

        ownerService.deleteOwner(owner);
    }

    @Test
    void shouldDeleteOwner() {
        Owner owner = ownerService.create(new Owner("test", "te@gm.c", "000 00 00 00", "000000000000"));
        ownerService.deleteOwner(owner);
        assertThrows(ObjectDoesNotExistException.class, () -> {
            ownerService.deleteOwner(owner);
        });
    }

    @Test
    void shoulUpdateOwner() {
        Owner owner = ownerService.create(new Owner("test", "te@gm.c", "000 00 00 00", "000000000000"));
        Owner uOwner = ownerService.updateOwner(new UpdateOwnerDto(owner.getId(), "uTest", "u@gm.c",
                "111 11 11 11", "111111111111"));

        assertEquals("uTest", uOwner.getOwnerName());
        assertEquals("u@gm.c", uOwner.getOwnerEmail());
        assertEquals("111 11 11 11", uOwner.getOwnerPhoneNumber());
        assertEquals("111111111111", uOwner.getOwnerNIP());

        ownerService.deleteOwner(uOwner);
    }

    @Test
    void shouldReturnOwnerById() {
        Owner owner = ownerService.create(new Owner("test", "te@gm.c", "000 00 00 00", "000000000000"));
        assertNotNull(ownerService.findOwnerById(owner.getId()));

        ownerService.deleteOwner(owner);
    }

    @Test
    void findOwnerByNIP() {
        Owner owner = ownerService.create(new Owner("test", "te@gm.c", "000 00 00 00", "000000000000"));
        assertNotNull(ownerService.findOwnerByNIP(owner.getOwnerNIP()));

        ownerService.deleteOwner(owner);
    }

    @Test
    void isUnique() {
        Owner owner = ownerService.create(new Owner("test", "te@gm.c", "000 00 00 00", "000000000000"));

        assertThrows(ObjectAlreadyExistsException.class, () -> {
            ownerService.isUnique(owner.getOwnerEmail(), owner.getOwnerNIP());
        });

        ownerService.deleteOwner(owner);

        assertTrue(ownerService.isUnique(owner.getOwnerEmail(), owner.getOwnerNIP()));
    }
}