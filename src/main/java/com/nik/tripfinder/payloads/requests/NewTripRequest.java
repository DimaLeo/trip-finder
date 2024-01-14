package com.nik.tripfinder.payloads.requests;

import com.nik.tripfinder.models.Trip;

public class NewTripRequest {

    private Trip trip;
    private Integer agencyId;

    public NewTripRequest(Trip trip, Integer agencyId) {
        this.trip = trip;
        this.agencyId = agencyId;
    }

    public Trip getTrip() {
        return trip;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

}
