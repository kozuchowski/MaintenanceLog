package com.maintenecelog.maintenancelog.repository;

import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {


    @Query("update Machine m set m.UDTNumber = ?1, m.VINNumber = ?2, m.serialNumber = ?3, m.dateOfManufacture = ?4," +
            "m.lastUDOExamination = ?5, m.UDTExaminationResult = ?6, m.lastMaintenance = ?7, m.maintainerExaminationResult = ?8," +
            "m.manufacturer = ?9, m.mainteiner = ?10 where m.id = ?11")
    void setMachine (String UDT, String VIN, String serial, LocalDate manufactured, LocalDate lastUDTEx,
                               boolean UDTExResult, LocalDate lastMaintenance, boolean mainteinerExResult,
                               String manufacturer, Mainteiner mainteiner, Long id);
    void deleteById(Long id);

    List<Machine> findAllByMainteiner(Mainteiner mainteiner);

}
