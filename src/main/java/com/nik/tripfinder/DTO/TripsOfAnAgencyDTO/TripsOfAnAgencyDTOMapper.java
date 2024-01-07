package com.nik.tripfinder.DTO.TripsOfAnAgencyDTO;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nik.tripfinder.models.Trip;

@Service
public class TripsOfAnAgencyDTOMapper {

    public static List<TripsOfAnAgencyDTO> mapToDTOList(List<Trip> trips) {
        return trips.stream()
                .map(trip -> new TripsOfAnAgencyDTO(trip.getStartDate(),
                        trip.getEndDate(),
                        trip.getDeparturePoint(),
                        trip.getDestination(),
                        trip.getTripSchedule(),
                        trip.getAgency().getBrandName(),
                        trip.getAgency().getOwner(),
                        trip.getMaxParticipants()))
                .collect(Collectors.toList());
    }

}