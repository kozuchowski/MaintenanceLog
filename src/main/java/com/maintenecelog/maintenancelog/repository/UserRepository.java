package com.maintenecelog.maintenancelog.repository;

import com.maintenecelog.maintenancelog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    User findByLogin(String login);

    void deleteByLogin(String login);

    @Query("select m from User m where m.licenceNumber = ?1 or m.email = ?2 or m.login = ?3")
    User findByUnique(String licence, String email, String login);

    Optional<User> findByUsername(String username);
}
