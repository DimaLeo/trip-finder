package com.nik.tripfinder.payloads.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nik.tripfinder.DTO.TripDTO.TripDTO;

import java.util.List;

public class AgencyTrips {
    @JsonProperty("brand_name")
    private String brandName;
    String owner;
    List<TripDTO> trips;

    public AgencyTrips(String brandName, String owner, List<TripDTO> trips) {
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
