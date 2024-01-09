package com.nik.tripfinder.DTO.TripDTO;

import com.nik.tripfinder.DTO.AgencyDTO.AgencyDTOMapper;
import com.nik.tripfinder.DTO.AgencyDTO.MinimalAgencyDTO;
import com.nik.tripfinder.models.Agency;
import com.nik.tripfinder.models.Trip;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TripDTOMapper implements Function<Trip, TripDTO> {

    private final AgencyDTOMapper agencyDTOMapper;

    public TripDTOMapper(AgencyDTOMapper agencyDTOMapper) {
        this.agencyDTOMapper = agencyDTOMapper;
    }

    @Override
    public TripDTO apply(Trip trip) {
        return new TripDTO(
                trip.getId(),
                trip.getStartDate(),
                trip.getEndDate(),
                trip.getDepartureArea(),
                trip.getDestination(),
                trip.getTripSchedule(),
                trip.getMaxParticipants(), 
                agencyDTOMapper.apply(trip.getAgency())
        );
    }

    public List<TripDTO> mapToDTOList(List<Trip> trips) {
        return trips.stream()
                .map(trip -> apply(trip))
                .collect(Collectors.toList());

    }
}


/*
 * new TripDTO(trip.getId(),
                        trip.getStartDate(),
                        trip.getEndDate(),
                        trip.getDepartureArea(),
                        trip.getDestination(),
                        trip.getTripSchedule(),
                        trip.getMaxParticipants(),
                        agencyDTOMapper.apply(trip.getAgency()))
 */