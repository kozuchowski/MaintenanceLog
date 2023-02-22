package com.maintenecelog.maintenancelog.repository;

import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {



    void deleteById(Long id);

    List<Machine> findAllByMainteiner(Mainteiner mainteiner);


    List<Machine> findAllByOwner(Owner owner);

    @Query("select m from Machine m where m.UDTNumber = ?1 or m.VINNumber = ?2 or m.serialNumber = ?3")
    Machine findByUnique(String UDT, String VIN, String Serial);

}
