package com.maintenecelog.maintenancelog.repository;

import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {


    @Modifying
    @Transactional
    @Query("update Machine m set m.UDTNumber = ?1, m.VINNumber = ?2, m.serialNumber = ?3, m.dateOfManufacture = ?4," +
            "m.lastUDOExamination = ?5, m.UDTExaminationResult = ?6, m.lastMaintenance = ?7, m.maintainerExaminationResult = ?8," +
            "m.manufacturer = ?9, m.mainteiner = ?10, m.owner = ?11 where m.id = ?12")
    void setMachine (String UDT, String VIN, String serial, LocalDate manufactured, LocalDate lastUDTEx,
                               boolean UDTExResult, LocalDate lastMaintenance, boolean mainteinerExResult,
                               String manufacturer, Mainteiner mainteiner,Owner owner, Long id);
    @Modifying
    @Transactional
    @Query("update Machine m set m.mainteiner = ?1 where m.id = ?2")
    void setMachine (Mainteiner mainteiner,Long id);

    @Modifying
    @Transactional
    void deleteById(Long id);

    List<Machine> findAllByMainteiner(Mainteiner mainteiner);

    @Query("select m from Machine m where m.owner = ?1 ")
    List<Machine> findAllByOwner(Owner owner);

    @Query("select m from Machine m where m.UDTNumber = ?1 or m.VINNumber = ?2 or m.serialNumber = ?3")
    Machine findByUnique(String UDT, String VIN, String Serial);

}
