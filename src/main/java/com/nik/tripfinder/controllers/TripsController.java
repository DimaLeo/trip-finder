package com.nik.tripfinder.controllers;

import com.nik.tripfinder.DTO.CustomerDTO.CustomerDTO;
import com.nik.tripfinder.DTO.TripDTO.TripDTO;
import com.nik.tripfinder.exceptions.GeneralException;
import com.nik.tripfinder.payloads.requests.NewTripRequest;
import com.nik.tripfinder.services.TripsService;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/trips")
public class TripsController {

    private final TripsService tripsService;

    public TripsController(TripsService tripsService) {
        this.tripsService = tripsService;
    }

    // Create a new trip
    @PostMapping("")
    public ResponseEntity<TripDTO> createTrip(@RequestBody NewTripRequest trip) throws GeneralException {
        TripDTO newTrip = tripsService.save(trip);
        return new ResponseEntity<>(newTrip, HttpStatus.CREATED);
    }

    // Gets all the trips filtered by the given parameters
    @GetMapping("")
    public ResponseEntity<List<TripDTO>> getTrips(
            @RequestParam(required = false) Long startDate,
            @RequestParam(required = false) Long endDate,
            @RequestParam(required = false) String destination,
            @RequestParam(required = false) String departureArea,
            @RequestParam(required = false) Integer agencyId,
            @RequestParam(required = false) Integer customerId) throws GeneralException {

        List<TripDTO> trips = tripsService.findTripsWithOptionalParameters(startDate, endDate, destination, departureArea, agencyId, customerId);
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    // Get the destinations of all trips
    @GetMapping("/destinations")
    public ResponseEntity<List<String>> getAllDestinations() throws GeneralException {
        return new ResponseEntity<>(tripsService.getAllDestinations(), HttpStatus.OK);
    }

    // Get the departure areas of all trips
    @GetMapping("/departure-areas")
    public ResponseEntity<List<String>> getAllDepartureAreas() throws GeneralException {
        return new ResponseEntity<>(tripsService.getAllDepartureAreas(), HttpStatus.OK);
    }

    // Delete a trip
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id) throws GeneralException {
        tripsService.deleteTrip(id);
        return ResponseEntity.noContent().build();
    }

    // Get the reservations of a trip
    @GetMapping("/{trip_id}/reservations")
    public ResponseEntity<List<CustomerDTO>> getTripReservations(@PathVariable(name = "trip_id") Long tripId) throws GeneralException {
        return new ResponseEntity<>(tripsService.getTripReservations(tripId), HttpStatus.OK);
    }

}
