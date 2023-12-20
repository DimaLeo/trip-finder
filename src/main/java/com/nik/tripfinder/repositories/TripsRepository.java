package com.nik.tripfinder.repositories;

import com.nik.tripfinder.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripsRepository extends JpaRepository<Trip, String> {
}
