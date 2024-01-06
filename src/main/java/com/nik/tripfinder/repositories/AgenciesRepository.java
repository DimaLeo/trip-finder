package com.nik.tripfinder.repositories;

import com.nik.tripfinder.models.Agency;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AgenciesRepository extends JpaRepository<Agency, String> {
	Optional<Agency> findAgencyByTaxCode(String agency);
}
