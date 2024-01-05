package com.nik.tripfinder.payloads.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewReservationRequest {

    String email;
    @JsonProperty("trip_id")
    Long tripId;

    public String getEmail() {
        return email;
    }

    public Long getTripId() {
        return tripId;
    }
}
