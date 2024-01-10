package com.nik.tripfinder.payloads.requests;

public class NewReservationRequest {

    Integer userId;
    Long tripId;

    public Integer getUserId() {
        return userId;
    }

    public Long getTripId() {
        return tripId;
    }
}
