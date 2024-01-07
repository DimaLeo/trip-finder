package com.nik.tripfinder.payloads.responses;

import com.nik.tripfinder.payloads.Objects.CustomerReservation;

import java.util.List;

public class CustomerReservationsResponse extends GenericResponse{

    private List<CustomerReservation> reservations;

    public CustomerReservationsResponse(String status, String message) {
        super(status, message);
    }

    public CustomerReservationsResponse(String status, String message, List<CustomerReservation> reservations) {
        super(status, message);
        this.reservations = reservations;
    }

    public List<CustomerReservation> getReservations() {
        return reservations;
    }
}
