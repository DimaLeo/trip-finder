package com.nik.tripfinder.payloads.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewReservationRequest {

    String username;
    @JsonProperty("trip_id")
    Long tripId;

    public String getUsername() {
        return username;
    }

    public Long getTripId() {
        return tripId;
    }
}
