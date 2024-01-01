package com.nik.tripfinder.DTO.CustomerDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CustomerDTO(
        String username,
        @JsonProperty("user_type")
        String userType,
        @JsonProperty("tax_code")
        String taxCode,
        String name,
        String surname,
        String email
) {}
