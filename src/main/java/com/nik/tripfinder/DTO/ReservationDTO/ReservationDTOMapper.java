package com.nik.tripfinder.DTO.ReservationDTO;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nik.tripfinder.DTO.TripDTO.TripDTOMapper;
import com.nik.tripfinder.models.Reservation;

@Service
public class ReservationDTOMapper implements Function<Reservation, ReservationDTO> {

    private final TripDTOMapper tripDTOMapper;

    public ReservationDTOMapper(TripDTOMapper tripDTOMapper) {
        this.tripDTOMapper = tripDTOMapper;
    }   

    @Override
    public ReservationDTO apply(Reservation r) {
        return new ReservationDTO(r.getReservationId(), tripDTOMapper.apply(r.getTrip()));
    }

    public List<ReservationDTO> mapToDTOList(List<Reservation> reservations) {
        return reservations.stream().map(r -> apply(r)).collect(Collectors.toList());

    }
}
