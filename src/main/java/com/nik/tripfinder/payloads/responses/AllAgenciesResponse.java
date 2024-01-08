package com.nik.tripfinder.payloads.responses;

import com.nik.tripfinder.DTO.AgencyDTO.MinimalAgencyDTO;

import java.util.List;

public class AllAgenciesResponse extends GenericResponse{

    private List<MinimalAgencyDTO> body;

    public AllAgenciesResponse(String status, String message) {
        super(status, message);
    }

    public AllAgenciesResponse(String status, String message, List<MinimalAgencyDTO> body) {
        super(status, message);
        this.body = body;
    }

    public List<MinimalAgencyDTO> getBody() {
        return body;
    }
}
