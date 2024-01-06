package com.nik.tripfinder.controllers;


import com.nik.tripfinder.models.Agency;
import com.nik.tripfinder.payloads.responses.AllAgenciesResponse;
import com.nik.tripfinder.payloads.responses.TripsOfAnAgencyResponse;
import com.nik.tripfinder.services.AgenciesService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AgencyController {

	@Autowired
    private final AgenciesService agenciesService;

    public AgencyController(AgenciesService agenciesService) {
        this.agenciesService = agenciesService;
    }

    
	
	@GetMapping(path="/agencies")
	public AllAgenciesResponse getAllAgencies()  throws Exception{
		return agenciesService.getAllAgencies();
	} 
	
	@PostMapping(path="/addAgency")
	public void addAgency(@RequestBody Agency a) throws Exception {
		agenciesService.addAgency(a);
	}
	
	@GetMapping(path="/{agency}/trips")
	public TripsOfAnAgencyResponse findTrips(@PathVariable String agency) throws Exception {
		return agenciesService.findTrips(agency);
		
	}
	
    // /api/agencies
    // GET /: get all the agencies
    // GET /agencies/{agency}Id/trips: get all trips of an agency

}
