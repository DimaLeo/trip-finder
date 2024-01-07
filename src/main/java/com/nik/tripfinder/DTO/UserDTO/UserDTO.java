package com.nik.tripfinder.DTO.UserDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserDTO(
        String username,
        @JsonProperty("user_type")
        String userType
) {
}
