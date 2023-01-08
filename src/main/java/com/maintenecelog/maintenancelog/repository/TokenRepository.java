package com.maintenecelog.maintenancelog.repository;

import com.maintenecelog.maintenancelog.model.Mainteiner;
import com.maintenecelog.maintenancelog.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long>{

    @Query("select t from Token t where t.mainteiner = ?1")
    Token findByMaintainer (Mainteiner mainteiner);

    void deleteByToken(Token token);

    @Query("select t from Token t where  t.token = ?1")
    Token findByToken(String token);
}
