package com.nik.tripfinder.repositories;

import com.nik.tripfinder.models.Agency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgenciesRepository extends JpaRepository<Agency, String> {

    Optional<Agency> findAgencyByBrandNameOrTaxCode(String brandName, String taxCode);
    Optional<Agency> findAgencyByAgencyId(Integer id);

    Optional<Agency> findAgencyByTaxCode(String agency);

}
