package com.nik.tripfinder.payloads.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AuthenticationRequest {


    @NotEmpty
    @NotBlank
    @NotNull
    private String username;
    @NotBlank
    @NotEmpty
    @NotNull
    private String password;

    public AuthenticationRequest(){

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
