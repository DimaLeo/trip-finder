package com.nik.tripfinder.payloads.responses;

import com.nik.tripfinder.DTO.TripDTO.TripDTO;

public class NewTripResponse extends GenericResponse{
    private TripDTO body;
    public NewTripResponse(String status, String message) {
        super(status, message);
    }

    public NewTripResponse(String status, String message, TripDTO body) {
        super(status, message);
        this.body = body;
    }

    public TripDTO getBody() {
        return body;
    }
}
