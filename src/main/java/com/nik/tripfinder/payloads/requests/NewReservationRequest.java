package com.nik.tripfinder.payloads.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewReservationRequest {

    Integer userId;
    @JsonProperty("trip_id")
    Long tripId;

    public Integer getUserId() {
        return userId;
    }

    public Long getTripId() {
        return tripId;
    }
}
