package com.nik.tripfinder.DTO.TripDTO;

import com.nik.tripfinder.models.Trip;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TripDTOMapper implements Function<Trip, TripDTO> {

    @Override
    public TripDTO apply(Trip trip) {
        return new TripDTO(
                trip.getId(),
                trip.getStartDate(),
                trip.getEndDate(),
                trip.getDepartureArea(),
                trip.getDestination(),
                trip.getTripSchedule(),
                trip.getMaxParticipants()
        );
    }
}
