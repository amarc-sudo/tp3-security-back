package com.uqac.back.repository;

import com.uqac.back.beans.SecurityDatum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityDatumRepository extends JpaRepository<SecurityDatum, Integer> {
}