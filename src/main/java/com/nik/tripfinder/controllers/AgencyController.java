package com.nik.tripfinder.controllers;

import com.nik.tripfinder.payloads.responses.AgencyTripsResponse;
import com.nik.tripfinder.payloads.responses.AllAgenciesResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nik.tripfinder.services.AgenciesService;

@RestController
public class AgencyController {

    private final AgenciesService agenciesService;

    public AgencyController(AgenciesService agenciesService) {
        this.agenciesService = agenciesService;
    }

    @GetMapping(path = "/agencies")
    public ResponseEntity<AllAgenciesResponse> getAllAgencies() throws Exception {
        return new ResponseEntity<>(agenciesService.getAllAgencies(), HttpStatus.OK);
    }

    // @GetMapping(path = "/{user_id}/trips")
    // public ResponseEntity<AgencyTripsResponse> findTrips(@PathVariable Integer user_id) throws Exception {
    //     return new ResponseEntity<>(agenciesService.findTrips(user_id), HttpStatus.OK);

    // }

    // /api/agencies
    // GET /: get all the agencies
    // GET /{agencyId}/trips: get all trips of an agency


}
