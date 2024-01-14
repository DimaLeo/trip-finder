package com.nik.tripfinder.payloads.requests;

public class NewReservationRequest {

    Integer customerId;
    Long tripId;

    public Integer getCustomerId() {
        return customerId;
    }

    public Long getTripId() {
        return tripId;
    }
}
