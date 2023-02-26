package com.maintenecelog.maintenancelog.repository;

import com.maintenecelog.maintenancelog.model.Examinator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InspectorRepository extends JpaRepository<Examinator, Long> {

    Examinator findByFirstNameAndSurnameAndPhoneNumber(String name, String surname, String phone);
}
