package com.nik.tripfinder.repositories;

import com.nik.tripfinder.models.Trip;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TripsRepository extends JpaRepository<Trip, Long> {

    @Query("SELECT DISTINCT t.destination FROM Trip t")
    public List<String> findAllDestinations(); 

    @Query("SELECT DISTINCT t.departurePoint FROM Trip t")
    public List<String> findAllDeparturePoints();

}
