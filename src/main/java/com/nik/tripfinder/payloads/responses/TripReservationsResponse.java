package com.nik.tripfinder.payloads.responses;

import java.util.List;

public class TripReservationsResponse extends GenericResponse{

    private List<Integer> reservations;

    public TripReservationsResponse(String status, String message) {
        super(status, message);
    }

    public TripReservationsResponse(String status, String message, List<Integer> reservations) {
        super(status, message);
        this.reservations = reservations;
    }

    public List<Integer> getReservations() {
        return reservations;
    }
}
