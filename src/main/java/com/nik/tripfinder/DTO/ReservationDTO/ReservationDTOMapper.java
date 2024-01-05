package com.nik.tripfinder.DTO.ReservationDTO;

import com.nik.tripfinder.DTO.CustomerDTO.CustomerDTO;
import com.nik.tripfinder.models.Customer;
import com.nik.tripfinder.models.Reservation;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ReservationDTOMapper implements Function<Reservation, ReservationDTO> {
    @Override
    public ReservationDTO apply(Reservation reservation) {
        return new ReservationDTO(
                reservation.getId(),
                reservation.getCustomer().getName(),
                reservation.getCustomer().getSurname(),
                reservation.getCustomer().getEmail(),
                reservation.getTrip().getDestination(),
                reservation.getTrip().getStartDate()
        );
    }
}
