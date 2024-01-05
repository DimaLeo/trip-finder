package com.nik.tripfinder.controllers;

import com.nik.tripfinder.payloads.requests.NewReservationRequest;
import com.nik.tripfinder.payloads.responses.ReservationsConfirmationResponse;
import com.nik.tripfinder.services.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @PostMapping("/create-reservation")
    public ResponseEntity<ReservationsConfirmationResponse> createReservation(@RequestBody NewReservationRequest body) {

        ReservationsConfirmationResponse responseBody = reservationService.createReservation(body);


        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }



}
