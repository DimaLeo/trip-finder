package com.nik.tripfinder.payloads.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nik.tripfinder.DTO.TripDTO.TripDTO;

public class CustomerReservation {
    @JsonProperty("reservation_id")
    private Integer reservationId;
    private TripDTO trip;

    public CustomerReservation(Integer reservationId, TripDTO trip) {
        this.reservationId = reservationId;
        this.trip = trip;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public TripDTO getTrip() {
        return trip;
    }
}
