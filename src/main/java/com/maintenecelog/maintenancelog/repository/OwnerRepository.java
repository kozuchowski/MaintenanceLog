package com.maintenecelog.maintenancelog.repository;
import com.maintenecelog.maintenancelog.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    //@TODO Create methods
}
