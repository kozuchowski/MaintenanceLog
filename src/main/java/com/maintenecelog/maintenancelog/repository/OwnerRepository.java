package com.maintenecelog.maintenancelog.repository;
import com.maintenecelog.maintenancelog.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Owner findOwnerByOwnerName(String name);

    Owner findOwnerByOwnerNIP(String nip);
    void delete(Owner owner);

    @Query("update Owner o set o.ownerName = ?1, o.ownerEmail = ?2, o.ownerPhoneNumber = ?3, o.ownerNIP = ?4 where o.id = ?5")
    void updateOwner(String name, String email, String phone, String NIP, Long id);


    @Query("select o from Owner o where o.ownerEmail = ?1 or o.ownerNIP = ?2")
    Owner findByUnique(String email, String NIP);


}
