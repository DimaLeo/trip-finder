package com.nik.tripfinder.controllers;

import com.nik.tripfinder.exceptions.GeneralException;
import com.nik.tripfinder.models.Reservation;
import com.nik.tripfinder.payloads.requests.NewReservationRequest;
import com.nik.tripfinder.services.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    // Create a new reservation
    @PostMapping("/create")
    public ResponseEntity<Reservation> createReservation(@RequestBody NewReservationRequest body) throws GeneralException {
        return new ResponseEntity<>(reservationService.createReservation(body), HttpStatus.CREATED);
    }

    // Delete a reservation
    @DeleteMapping("/cancel/{reservation_id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Integer reservation_id) throws GeneralException {
        reservationService.cancelReservation(reservation_id);
        return ResponseEntity.noContent().build();
    }

}
