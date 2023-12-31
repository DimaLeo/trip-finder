package com.nik.tripfinder.payloads.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReservationsConfirmationResponse extends GenericResponse{

    @JsonProperty("reservation_id")
    private Integer reservationId;
    @JsonProperty("trip_id")
    private Long tripId;

    public ReservationsConfirmationResponse(String status, String message) {
        super(status, message);
    }

    public ReservationsConfirmationResponse(String status, String message, Integer reservationId, Long tripId) {
        super(status, message);
        this.reservationId = reservationId;
        this.tripId = tripId;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public Long getTripId() {
        return tripId;
    }
}
