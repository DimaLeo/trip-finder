package com.nik.tripfinder.repositories;

import com.nik.tripfinder.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Optional<Reservation> findReservationByCustomerCustomerIdAndTripId(Integer customerId, Long tripId);
    void deleteReservationByReservationId(Integer reservationId);
    Boolean existsReservationByReservationId(Integer reservationId);
}
