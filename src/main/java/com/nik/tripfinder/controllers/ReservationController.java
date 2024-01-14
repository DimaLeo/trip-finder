package com.nik.tripfinder.controllers;

import com.nik.tripfinder.exceptions.GeneralException;
import com.nik.tripfinder.models.Reservation;
import com.nik.tripfinder.payloads.requests.NewReservationRequest;
import com.nik.tripfinder.payloads.responses.CustomerReservationsResponse;
import com.nik.tripfinder.payloads.responses.ReservationsConfirmationResponse;
import com.nik.tripfinder.payloads.responses.TripReservationsResponse;
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

    @PostMapping("/create")
    public ResponseEntity<Reservation> createReservation(@RequestBody NewReservationRequest body) throws Exception {
        Reservation reservation = reservationService.createReservation(body);
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

    // @GetMapping("/trip/{trip_id}")
    // public ResponseEntity<TripReservationsResponse> getTripReservations(@PathVariable(name = "trip_id") Long trip_id) throws GeneralException {

    //     TripReservationsResponse responseBody = reservationService.getTripReservations(trip_id);


    //     return new ResponseEntity<>(responseBody, HttpStatus.OK);
    // }

    // @GetMapping("/customer/{customer_id}")
    // public ResponseEntity<CustomerReservationsResponse> getCustomerReservations(@PathVariable(name = "customer_id") Integer customer_id) throws GeneralException {

    //     CustomerReservationsResponse responseBody = reservationService.getCustomerReservations(customer_id);

    //     return new ResponseEntity<>(responseBody, HttpStatus.OK);
    // }

    @DeleteMapping("/cancel/{reservation_id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Integer reservation_id) throws Exception {
        reservationService.cancelReservation(reservation_id);
        return ResponseEntity.noContent().build();
    }

}
