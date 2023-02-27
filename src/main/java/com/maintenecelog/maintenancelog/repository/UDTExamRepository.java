package com.maintenecelog.maintenancelog.repository;

import com.maintenecelog.maintenancelog.model.UDTExamination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UDTExamRepository extends JpaRepository<UDTExamination, Long> {

}
