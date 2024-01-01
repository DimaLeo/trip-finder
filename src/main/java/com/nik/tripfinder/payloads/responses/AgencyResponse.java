package com.nik.tripfinder.payloads.responses;

import com.nik.tripfinder.DTO.AgencyDTO.AgencyDTO;

public class AgencyResponse extends GenericResponse{

    private AgencyDTO body;
    public AgencyResponse(String status, String message, AgencyDTO body) {
        super(status, message);
        this.body = body;
    }

    public AgencyResponse(String status, String message) {
        super(status, message);
    }

    public AgencyDTO getBody() {
        return body;
    }
}
