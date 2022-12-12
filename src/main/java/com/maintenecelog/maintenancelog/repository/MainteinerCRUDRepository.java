package com.maintenecelog.maintenancelog.repository;

import com.maintenecelog.maintenancelog.model.Maintener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainteinerCRUDRepository extends JpaRepository<Maintener, Long> {
}
