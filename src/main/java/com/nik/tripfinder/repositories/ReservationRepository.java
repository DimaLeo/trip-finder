package com.nik.tripfinder.repositories;

import com.nik.tripfinder.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Integer countByTripId(Long tripId);
    Optional<Reservation> findReservationByCustomerCustomerIdAndTripId(Integer customerId, Long tripId);
    List<Reservation> findReservationsByTripId(Long tripId);
    List<Reservation> findReservationsByCustomerCustomerId(Integer customerId);
    void deleteReservationByReservationId(Integer reservationId);
    Boolean existsReservationByReservationId(Integer reservationId);
}
