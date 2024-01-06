package com.nik.tripfinder.payloads.responses;


import java.util.List;

import com.nik.tripfinder.DTO.TripsOfAnAgencyDTO.TripsOfAnAgencyDTO;
import com.nik.tripfinder.models.Trip;

public class TripsOfAnAgencyResponse extends GenericResponse {
    private List<Trip> body;

	public TripsOfAnAgencyResponse(String status, String message, List<Trip> trips) {//TripsOfAnAgencyDTO trips){//List<Trip> trips) {
        super(status, message);
        this.body = trips;
    }
	
	public TripsOfAnAgencyResponse(String status, String message) {
        super(status, message);
    }
	
	public List<Trip> getBody() {
        return body;
    }
}
