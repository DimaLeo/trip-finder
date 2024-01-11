package com.nik.tripfinder.services;

import com.nik.tripfinder.DTO.TripDTO.TripDTO;
import com.nik.tripfinder.DTO.TripDTO.TripDTOMapper;
import com.nik.tripfinder.models.Agency;
import com.nik.tripfinder.models.Reservation;
import com.nik.tripfinder.models.Trip;
import com.nik.tripfinder.payloads.requests.NewTripRequest;
import com.nik.tripfinder.repositories.AgenciesRepository;
import com.nik.tripfinder.repositories.ReservationRepository;
import com.nik.tripfinder.repositories.TripsRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class TripsService {

    private final TripsRepository tripsRepository;
    private final AgenciesRepository agenciesRepository;
    private final ReservationRepository reservationRepository;
    private final TripDTOMapper tripDTOMapper;

    public TripsService(TripsRepository tripsRepository, AgenciesRepository agenciesRepository, ReservationRepository reservationRepository,
                        TripDTOMapper tripDTOMapper) {
        this.tripsRepository = tripsRepository;
        this.agenciesRepository = agenciesRepository;
        this.reservationRepository = reservationRepository;
        this.tripDTOMapper = tripDTOMapper;
    }

    public TripDTO save(NewTripRequest trip) throws Exception {

        Agency dbAgency;

        try {
            Optional<Agency> agencyOptional = agenciesRepository.findById(trip.getAgencyId());

            if (!agencyOptional.isPresent()) {
                throw new Exception("Failed to retrieve the agency from db.");
            }

            dbAgency = agencyOptional.get();
        } catch (Exception e) {
            throw new Exception("Failed while trying to retrieve agency info");
        }

        try {
            Trip newTrip = new Trip(
                    trip.getStartDate(),
                    trip.getEndDate(),
                    trip.getDepartureArea(),
                    trip.getDestination(),
                    trip.getTripSchedule(),
                    trip.getMaxParticipants(),
                    dbAgency);

            newTrip = tripsRepository.save(newTrip);

            return tripDTOMapper.apply(newTrip);

        } catch (Exception e) {
            throw new Exception("Error while creating the new trip");
        }
    }

    public List<TripDTO> findTripsWithOptionalParameters(
            Long startDate,
            Long endDate,
            String destination,
            String departureArea,
            Integer agencyId,
            Integer customerId) {

        List<TripDTO> trips =
                tripDTOMapper.mapToDTOList(
                        tripsRepository
                                .findTripsWithOptionalParameters(
                                        startDate,
                                        endDate,
                                        destination,
                                        departureArea,
                                        agencyId));

        List<Reservation> reservations= reservationRepository.findReservationsByCustomerCustomerId(customerId);

        for (TripDTO trip: trips){
            if (!reservations.isEmpty()) {
                for (Reservation r: reservations) {
                    if (r.getCustomer().getCustomerId() == customerId && r.getTrip().getId() == trip.getId()) {
                        trip.setReservationId(r.getReservationId());
                        reservations.remove(r);
                        break;
                    }
                }
            } else {
                break;
            }
        }

        return trips;
    }

    public List<String> getAllDestinations() {
        return tripsRepository.findAllDestinations();
    }

    public List<String> getAllDepartureAreas() {
        return tripsRepository.findAllDepartureAreas();
    }

    public void deleteTrip(Long id) throws EntityNotFoundException {
        Optional<Trip> tripOptional = tripsRepository.findById(id);
        if (tripOptional.isPresent()) {
            List<Reservation> tripReservations = reservationRepository.findReservationsByTripId(id);
            for (Reservation reservation : tripReservations) {
                reservationRepository.delete(reservation);
            }
            Trip tripToDelete = tripOptional.get();
            tripsRepository.delete(tripToDelete);
        } else {
            throw new EntityNotFoundException("Entity with id " + id + " not found");
        }
    }

}
