package com.maintenecelog.maintenancelog.repository;

import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.Maintenence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenence, Long> {

    List<Maintenence> findAllByMachineId(Long id);

    Maintenence findByExDate(LocalDate exDate);

    void deleteByExDateAndMachine(LocalDate exDate, Machine machine);


}
