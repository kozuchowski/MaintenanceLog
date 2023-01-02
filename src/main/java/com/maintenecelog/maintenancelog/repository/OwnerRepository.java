package com.maintenecelog.maintenancelog.repository;
import com.maintenecelog.maintenancelog.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    void delete(Owner owner);

    @Query("update Owner o set o.ownerName = ?1, o.email = ?2, o.phoneNumber = ?3, o.NIP = ?4 where o.id = ?5")
    void updateOwner(String name, String email, String phone, String NIP, Long id);



}
