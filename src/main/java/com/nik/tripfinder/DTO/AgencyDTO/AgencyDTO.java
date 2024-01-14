package com.nik.tripfinder.DTO.AgencyDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AgencyDTO(
        @JsonProperty("user_id")
        Integer userId, 
        String username,
        @JsonProperty("user_type")
        String userType,
        @JsonProperty("tax_code")
        String taxCode,
        @JsonProperty("brand_name")
        String brandName,
        String owner
) {}
