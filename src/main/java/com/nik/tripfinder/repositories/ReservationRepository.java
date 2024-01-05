package com.nik.tripfinder.repositories;

import com.nik.tripfinder.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    long countByTripId(Long tripId);
    Optional<Reservation> findReservationByCustomerIdAndTripId(Long customerId, Long tripId);
}
