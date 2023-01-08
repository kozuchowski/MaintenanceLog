package com.maintenecelog.maintenancelog.repository;

import com.maintenecelog.maintenancelog.model.Mainteiner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MainteinerRepository extends JpaRepository<Mainteiner, Long> {

    @Query("select m from Mainteiner m where m.login like ?1")
    Mainteiner findByLogin(String login);

    @Query("select t.mainteiner from Token t join Mainteiner m on t.mainteiner = m where t.token = ?1")
    Mainteiner findByToken(String token);

    @Modifying
    @Transactional
    @Query("delete from Mainteiner m where m.login like ?1")
    void deleteByLogin(String login);

    @Modifying
    @Transactional
    @Query("update Mainteiner m set m.name = ?1, m.surname = ?2, m.email = ?4, m.password = ?5, m.licenceNumber = ?6 where m.login = ?3")
    void setMaintainerByLogin(String name, String surname, String login, String email, String password, String licence);



}
