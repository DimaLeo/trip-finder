package com.nik.tripfinder.payloads.responses;

import com.nik.tripfinder.DTO.UserDTO.UserDTO;

public class AuthenticationResponse extends GenericResponse{

    private UserDTO user;
    private Integer id;

    public AuthenticationResponse(String status, String message) {
        super(status, message);
    }

    public AuthenticationResponse(String status, String message, UserDTO user, Integer id) {
        super(status, message);
        this.user = user;
        this. id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public Integer getId() {
        return id;
    }
}
