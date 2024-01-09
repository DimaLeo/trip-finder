package com.nik.tripfinder.repositories;

import com.nik.tripfinder.models.Agency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgenciesRepository extends JpaRepository<Agency, Integer> {

    Optional<Agency> findAgencyByBrandNameOrTaxCode(String brandName, String taxCode);
    Optional<Agency> findAgencyByAgencyId(Integer id);
    Optional<Agency> findAgencyByUserId(Integer userId);

    Optional<Agency> findAgencyByTaxCode(String agency);

}
