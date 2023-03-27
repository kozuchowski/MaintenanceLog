package com.maintenecelog.maintenancelog.repository;
import com.maintenecelog.maintenancelog.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Owner findOwnerByOwnerName(String name);

    Owner findOwnerByOwnerNIP(String nip);


    @Query("select o from Owner o where o.ownerEmail = ?1 or o.ownerNIP = ?2")
    Owner findByUnique(String email, String NIP);


}
