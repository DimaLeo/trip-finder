package com.nik.tripfinder.controllers;

import com.nik.tripfinder.DTO.TripDTO.TripDTO;
import com.nik.tripfinder.exceptions.GeneralException;
import com.nik.tripfinder.payloads.requests.NewTripRequest;
import com.nik.tripfinder.payloads.responses.TripReservationsResponse;
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

// /api/trips
@RestController
@RequestMapping("/trips")
public class TripsController {

    private final TripsService tripsService;

    public TripsController(TripsService tripsService) {
        this.tripsService = tripsService;
    }

    // POST /: create trip
    @PostMapping("")
    public ResponseEntity<TripDTO> createTrip(@RequestBody NewTripRequest trip) throws GeneralException {
        TripDTO newTrip = tripsService.save(trip);
        return new ResponseEntity<>(newTrip, HttpStatus.CREATED);
    }

    // GET /: get all trips
    // or filtered search of trips:
    // agencyId={agencyID}
    // startDate={startDate}
    // endDate={endDate}
    // destination={destination}
    // departureArea={departureArea} 
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

    // GET /destinations: get the destinations of all the trips
    @GetMapping("/destinations")
    public ResponseEntity<List<String>> getAllDestinations() throws GeneralException {
        List<String> destinations = tripsService.getAllDestinations();
        return new ResponseEntity<>(destinations, HttpStatus.OK);
    }

    // GET /departure-areas: get the departure areas of all the trips
    @GetMapping("/departure-areas")
    public ResponseEntity<List<String>> getAllDepartureAreas() throws GeneralException {
        List<String> departureAreas = tripsService.getAllDepartureAreas();
        return new ResponseEntity<>(departureAreas, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id) throws GeneralException {
        tripsService.deleteTrip(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{trip_id}/reservations")
    public ResponseEntity<TripReservationsResponse> getTripReservations(@PathVariable(name = "trip_id") Long trip_id) throws GeneralException {
        TripReservationsResponse responseBody = tripsService.getTripReservations(trip_id);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

}
