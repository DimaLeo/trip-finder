package com.nik.tripfinder.DTO.TripsOfAnAgencyDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TripsOfAnAgencyDTO (
        @JsonProperty("taxCode")
        String startDate,
        String endDate,
        String departurePoint,
        String destination,
        String tripSchedule,
        String brandName,
        String owner,
        Integer maxParticipants
) {}