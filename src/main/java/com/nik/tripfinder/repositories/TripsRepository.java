package com.nik.tripfinder.repositories;

import com.nik.tripfinder.models.Trip;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TripsRepository extends JpaRepository<Trip, Long> {

    @Query("SELECT DISTINCT t.destination FROM Trip t")
    public List<String> findAllDestinations(); 

    @Query("SELECT DISTINCT t.departurePoint FROM Trip t")
    public List<String> findAllDeparturePoints();

    @Query("SELECT t FROM Trip t " +
            "WHERE (:startDate IS NULL OR t.startDate >= :startDate) " +
            "AND (:endDate IS NULL OR t.endDate <= :endDate) " +
            "AND (:destination IS NULL OR t.destination = :destination) " +
            "AND (:departurePoint IS NULL OR t.departurePoint = :departurePoint)")
    List<Trip> findTripsWithOptionalParameters(
        @Param("startDate") Long startDate,
        @Param("endDate") Long endDate,
        @Param("destination") String destination,
        @Param("departurePoint") String departurePoint);

}
