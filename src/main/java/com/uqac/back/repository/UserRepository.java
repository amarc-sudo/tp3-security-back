package com.uqac.back.repository;

import com.uqac.back.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);

    User findByPasswordAndLogin(String passwordCrypt, String login);
}