package com.uqac.back.repository;

import com.uqac.back.beans.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    Optional<Token> findByToken(String token);

    @Query(value = "SELECT T FROM Token as T WHERE  T.token = ?1 and T.dateExpirationToken > ?2")
    Optional<Token> checkToken(String token, Instant now);

}