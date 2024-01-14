package com.nik.tripfinder.DTO.AgencyDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MinimalAgencyDTO(
        Integer id,
        @JsonProperty("brand_name")
        String brandName
) {}
