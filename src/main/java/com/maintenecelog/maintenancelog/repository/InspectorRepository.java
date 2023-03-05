package com.maintenecelog.maintenancelog.repository;

import com.maintenecelog.maintenancelog.model.Inspector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InspectorRepository extends JpaRepository<Inspector, Long> {

    Inspector findByFirstNameAndSurnameAndPhoneNumber(String name, String surname, String phone);
}
