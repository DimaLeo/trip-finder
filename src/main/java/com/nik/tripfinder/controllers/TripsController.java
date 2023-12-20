package com.nik.tripfinder.controllers;

import com.nik.tripfinder.services.TripsService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TripsController {

    private final TripsService tripsService;

    public TripsController(TripsService tripsService) {
        this.tripsService = tripsService;
    }
}
