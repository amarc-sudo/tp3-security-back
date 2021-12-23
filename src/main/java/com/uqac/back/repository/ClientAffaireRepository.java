package com.uqac.back.repository;

import com.uqac.back.beans.ClientAffaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientAffaireRepository extends JpaRepository<ClientAffaire, Integer> {
}