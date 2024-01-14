package com.nik.tripfinder.repositories;

import com.nik.tripfinder.models.Agency;
import com.nik.tripfinder.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AgenciesRepository extends JpaRepository<Agency, Integer> {

    Optional<Agency> findAgencyByBrandNameOrTaxCode(String brandName, String taxCode);
    Optional<Agency> findAgencyByUserId(Integer userId);
    Optional<Agency> findAgencyByTaxCode(String agency);

}
