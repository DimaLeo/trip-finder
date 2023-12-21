package com.nik.tripfinder.controllers;

import com.nik.tripfinder.services.TripsService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TripsController {

    private final TripsService tripsService;

    public TripsController(TripsService tripsService) {
        this.tripsService = tripsService;
    }

    // /api/trips
    // POST / : create trip
    // GET /api/trips/search?agencyID={agencyID}&startDate={startDate}&endDate={endDate}&destination={destination}&departurePoint={departurePoint}: filtered search of trips
    // GET /api/trips/{tripID}: get trip info
    // GET /api/trips/destinations: get the destinations of all the trips
    // GET /api/trips/departure-points: get the departure points of all the trips
    
}
