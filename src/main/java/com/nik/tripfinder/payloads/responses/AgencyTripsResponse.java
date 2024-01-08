package com.nik.tripfinder.payloads.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nik.tripfinder.DTO.TripDTO.TripDTO;

import java.util.List;

public class AgencyTripsResponse extends GenericResponse{

    @JsonProperty("brand_name")
    private String brandName;
    String owner;
    List<TripDTO> trips;

    public AgencyTripsResponse(String status, String message) {
        super(status, message);
    }

    public AgencyTripsResponse(String status, String message, String brandName, String owner, List<TripDTO> trips) {
        super(status, message);
        this.brandName = brandName;
        this.owner = owner;
        this.trips = trips;
    }


    public String getBrandName() {
        return brandName;
    }

    public String getOwner() {
        return owner;
    }

    public List<TripDTO> getTrips() {
        return trips;
    }

}
