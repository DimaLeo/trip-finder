package com.nik.tripfinder.controllers;

import com.nik.tripfinder.DTO.AgencyDTO.MinimalAgencyDTO;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping(path = "/")
    public ResponseEntity<List<MinimalAgencyDTO>> getAllAgencies() throws Exception {
        return new ResponseEntity<>(agenciesService.getAllAgencies(), HttpStatus.OK);
    }

    // /api/agencies
    // GET /: get all the agencies
    // GET /{agencyId}/trips: get all trips of an agency


}
