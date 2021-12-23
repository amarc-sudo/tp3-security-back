package com.uqac.back.repository;

import com.uqac.back.beans.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}