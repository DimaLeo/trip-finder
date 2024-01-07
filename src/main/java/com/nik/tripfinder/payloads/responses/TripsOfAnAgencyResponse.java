package com.nik.tripfinder.payloads.responses;

import java.util.List;

import com.nik.tripfinder.DTO.TripsOfAnAgencyDTO.TripsOfAnAgencyDTO;

public class TripsOfAnAgencyResponse extends GenericResponse {
    private List<TripsOfAnAgencyDTO> body;

    public TripsOfAnAgencyResponse(String status, String message, List<TripsOfAnAgencyDTO> trips) {
        super(status, message);
        this.body = trips;
    }

    public TripsOfAnAgencyResponse(String status, String message) {
        super(status, message);
    }

    public List<TripsOfAnAgencyDTO> getBody() {
        return body;
    }
}