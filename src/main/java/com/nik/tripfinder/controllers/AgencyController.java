package com.nik.tripfinder.controllers;

import com.nik.tripfinder.DTO.AgencyDTO.AgencyDTO;
import com.nik.tripfinder.DTO.AgencyDTO.MinimalAgencyDTO;

import java.util.List;

import com.nik.tripfinder.DTO.TripDTO.TripDTO;
import com.nik.tripfinder.exceptions.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nik.tripfinder.services.AgenciesService;

@RestController
@RequestMapping("/agencies")
public class AgencyController {

    private final AgenciesService agenciesService;

    public AgencyController(AgenciesService agenciesService) {
        this.agenciesService = agenciesService;
    }

    // Get all agencies
    @GetMapping(path = "")
    public ResponseEntity<List<MinimalAgencyDTO>> getAllAgencies() throws Exception {
        return new ResponseEntity<>(agenciesService.getAllAgencies(), HttpStatus.OK);
    }

    // Get all the trips of an agency with the given id
    @GetMapping(path = "/{id}/trips")
    public ResponseEntity<List<TripDTO>> getAgencyTrips(@PathVariable(name = "id") Integer id) throws GeneralException {
        List<TripDTO> trips = agenciesService.getAgencyTrips(id);
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    // Get the details of an agency with the given id
    @GetMapping(path = "/{id}")
    public ResponseEntity<AgencyDTO> getAgencyDetails(@PathVariable Integer id) throws GeneralException {
        return new ResponseEntity<>(agenciesService.getAgencyDetails(id), HttpStatus.OK);
    }
}
