package com.nik.tripfinder.payloads.responses;

import com.nik.tripfinder.DTO.CustomerDTO.CustomerDTO;

import java.util.List;

public class TripReservationsResponse extends GenericResponse{

    private List<Integer> reservations;
    private List<CustomerDTO> customers;

    public TripReservationsResponse(String status, String message) {
        super(status, message);
    }

    public TripReservationsResponse(String status, String message, List<Integer> reservations, List<CustomerDTO> customers) {
        super(status, message);
        this.reservations = reservations;
        this.customers = customers;
    }

    public List<Integer> getReservations() {
        return reservations;
    }

    public List<CustomerDTO> getCustomers() {
        return customers;
    }
}
