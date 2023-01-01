package com.maintenecelog.maintenancelog.repository;

import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.Maintenence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MaintenenceRepository extends JpaRepository<Maintenence, Long> {

    List<Maintenence> findAllByMachine (Machine machine);

    Maintenence findByExDate(LocalDate exDate);
    @Modifying
    @Transactional
    @Query("update Maintenence m set m.description = ?2 where m.exDate = ?1 and m.machine = ?3")
    void setMaintenanceByDate(LocalDate exDate, String description, Machine machine);

    @Modifying
    @Transactional
    @Query("delete Maintenence m where m.exDate = ?1 and m.machine = ?2")
    void deleteByDate(LocalDate exDate, Machine machine);


}
