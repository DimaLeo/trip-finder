package com.nik.tripfinder.controllers;

import com.nik.tripfinder.services.AgenciesService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AgencyController {

    private final AgenciesService agenciesService;

    public AgencyController(AgenciesService agenciesService) {
        this.agenciesService = agenciesService;
    }

    // /api/agencies
    // GET /: get all the agencies
    // GET /agencies/{agency}Id/trips: get all trips of an agency

}
