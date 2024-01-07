package com.nik.tripfinder.DTO.TripDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TripDTO(
        Long id,
        @JsonProperty("start_date")
        Long startDate,
        @JsonProperty("end_date")
        Long endDate,
        @JsonProperty("departure_point")
        String departurePoint,
        String destination,
        @JsonProperty("trip_schedule")
        String tripSchedule,
        @JsonProperty("max_participants")
        Integer maxParticipants
) {
}
