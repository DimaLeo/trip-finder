package com.nik.tripfinder.payloads.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nik.tripfinder.DTO.ReservationDTO.ReservationDTO;

import java.util.List;

public class ReservationsConfirmationResponse extends GenericResponse{

    @JsonProperty("reservation_id")
    private Long reservationId;
    @JsonProperty("trip_id")
    private Long tripId;

    public ReservationsConfirmationResponse(String status, String message) {
        super(status, message);
    }

    public ReservationsConfirmationResponse(String status, String message, Long reservationId, Long tripId) {
        super(status, message);
        this.reservationId = reservationId;
        this.tripId = tripId;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public Long getTripId() {
        return tripId;
    }
}
