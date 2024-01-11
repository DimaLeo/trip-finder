package com.nik.tripfinder.payloads.responses;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReservationsConfirmationResponse extends GenericResponse{

    @JsonProperty("reservation_id")
    private Integer reservationId;
    @JsonProperty("trip_id")
    private Long tripId;

    public ReservationsConfirmationResponse(String status, String message, HttpStatus statusCode) {
        super(status, message, statusCode);
    }

    public ReservationsConfirmationResponse(String status, String message, HttpStatus statusCode, Integer reservationId, Long tripId) {
        super(status, message, statusCode);
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
