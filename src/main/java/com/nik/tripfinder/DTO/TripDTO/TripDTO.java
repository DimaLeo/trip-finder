package com.nik.tripfinder.DTO.TripDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nik.tripfinder.DTO.AgencyDTO.AgencyDTO;

public record TripDTO(
        Long id,
        @JsonProperty("start_date")
        Long startDate,
        @JsonProperty("end_date")
        Long endDate,
        @JsonProperty("departure_area")
        String departureArea,
        String destination,
        @JsonProperty("trip_schedule")
        String tripSchedule,
        @JsonProperty("max_participants")
        Integer maxParticipants,
        @JsonProperty("agency")
        AgencyDTO agency
) {
}
