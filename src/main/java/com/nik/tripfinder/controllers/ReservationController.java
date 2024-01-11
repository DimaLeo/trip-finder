package com.nik.tripfinder.controllers;

import com.nik.tripfinder.payloads.requests.NewReservationRequest;
import com.nik.tripfinder.payloads.responses.CustomerReservationsResponse;
import com.nik.tripfinder.payloads.responses.ReservationsConfirmationResponse;
import com.nik.tripfinder.payloads.responses.TripReservationsResponse;
import com.nik.tripfinder.services.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @PostMapping("/create")
    public ResponseEntity<ReservationsConfirmationResponse> createReservation(@RequestBody NewReservationRequest body) throws Exception {

        ReservationsConfirmationResponse responseBody = reservationService.createReservation(body);


        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping("/trip/{trip_id}")
    public ResponseEntity<TripReservationsResponse> getTripReservations(@PathVariable(name = "trip_id") Long trip_id) {

        TripReservationsResponse responseBody = reservationService.getTripReservations(trip_id);


        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping("/customer/{customer_id}")
    public ResponseEntity<CustomerReservationsResponse> getCustomerReservations(@PathVariable(name = "customer_id") Integer customer_id) {

        CustomerReservationsResponse responseBody = reservationService.getCustomerReservations(customer_id);

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping("/cancel/{reservation_id}")
    public ResponseEntity<ReservationsConfirmationResponse> cancelReservation(@PathVariable(name = "reservation_jd") Integer reservation_id){

        ReservationsConfirmationResponse responseBody = reservationService.cancelReservation(reservation_id);

        return new ResponseEntity<>(responseBody, HttpStatus.OK);

    }



}
