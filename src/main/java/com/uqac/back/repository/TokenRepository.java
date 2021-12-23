package com.uqac.back.repository;

import com.uqac.back.beans.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    Optional<Token> findByTokenEquals(String token);

}