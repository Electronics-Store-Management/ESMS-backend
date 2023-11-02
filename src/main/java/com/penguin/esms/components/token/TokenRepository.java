package com.penguin.esms.components.token;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = """
        select t from Token t join StaffEntity s\s
        on t.user.id = s.id\s
        where s.id = :id and (t.expired = false or t.revoked = false)\s
        """)
    List<Token> findAllValidTokenByUser(String id);

}