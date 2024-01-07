package com.nik.tripfinder.controllers;

import com.nik.tripfinder.models.Trip;
import com.nik.tripfinder.services.TripsService;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// /api/trips
@RestController
@RequestMapping("/api/trips")
public class TripsController {

    private final TripsService tripsService;

    public TripsController(TripsService tripsService) {
        this.tripsService = tripsService;
    }

    // POST /: create trip
    @PostMapping("/")
    public ResponseEntity<Trip> createTrip(@RequestBody Trip trip) {
        Trip newTrip = tripsService.save(trip);
        return new ResponseEntity<>(newTrip, HttpStatus.CREATED);
    }

    // GET /: get all trips
    // or filtered search of trips:
    // agencyID={agencyID}
    // startDate={startDate}
    // endDate={endDate}
    // destination={destination}
    // departureArea={departureArea} 
    @GetMapping("/")
    public ResponseEntity<List<Trip>> getTrips(
            @RequestParam(required = false) Long startDate,
            @RequestParam(required = false) Long endDate,
            @RequestParam(required = false) String destination,
            @RequestParam(required = false) String departureArea) {

        List<Trip> trips = tripsService.findTripsWithOptionalParameters(startDate, endDate, destination, departureArea);

        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    // GET /{tripId}: get trip info
    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTrip(@PathVariable Long id) {
        Trip trip = tripsService.getTripInfo(id);

        if (trip != null) return new ResponseEntity<>(trip, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // GET /destinations: get the destinations of all the trips
    @GetMapping("/destinations")
    public ResponseEntity<List<String>> getAllDestinations() {
        List<String> destinations = tripsService.getAllDestinations();
        return new ResponseEntity<>(destinations, HttpStatus.OK);
    }

    // GET /departure-points: get the departure points of all the trips
    @GetMapping("/departure-areas")
    public ResponseEntity<List<String>> getAllDepartureAreas() {
        List<String> departureAreas = tripsService.getAllDepartureAreas();
        return new ResponseEntity<>(departureAreas, HttpStatus.OK);
    }

}
