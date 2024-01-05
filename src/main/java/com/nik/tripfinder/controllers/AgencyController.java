package com.nik.tripfinder.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.nik.tripfinder.services.AgenciesService;

@RestController
public class AgencyController {

    private final AgenciesService agenciesService;

    public AgencyController(AgenciesService agenciesService) {
        this.agenciesService = agenciesService;
    }

    // /api/agencies
    // GET /: get all the agencies
    // GET /{agencyId}/trips: get all trips of an agency


}
