package com.nik.tripfinder.payloads.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nik.tripfinder.DTO.TripDTO.TripDTO;
import com.nik.tripfinder.models.Trip;

public class NewTripRequest {
    @JsonProperty("user_id")
    private Integer userId;
    private TripDTO trip;

    public NewTripRequest(Integer userId, TripDTO trip) {
        this.userId = userId;
        this.trip = trip;
    }

    public Integer getUserId() {
        return userId;
    }

    public TripDTO getTrip() {
        return trip;
    }
}
