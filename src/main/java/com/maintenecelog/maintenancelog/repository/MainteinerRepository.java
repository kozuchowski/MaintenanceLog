package com.maintenecelog.maintenancelog.repository;

import com.maintenecelog.maintenancelog.model.Mainteiner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface MainteinerRepository extends JpaRepository<Mainteiner, Long> {


    Mainteiner findByLogin(String login);

    void deleteByLogin(String login);

    @Query("select m from Mainteiner m where m.licenceNumber = ?1 or m.email = ?2 or m.login = ?3")
    Mainteiner findByUnique(String licence, String email, String login);
}
