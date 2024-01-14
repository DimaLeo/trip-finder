package com.nik.tripfinder.services;

import com.nik.tripfinder.DTO.TripDTO.TripDTO;
import com.nik.tripfinder.DTO.TripDTO.TripDTOMapper;
import com.nik.tripfinder.exceptions.GeneralException;
import com.nik.tripfinder.models.Agency;
import com.nik.tripfinder.models.Reservation;
import com.nik.tripfinder.models.Trip;
import com.nik.tripfinder.payloads.requests.NewTripRequest;
import com.nik.tripfinder.repositories.AgenciesRepository;
import com.nik.tripfinder.repositories.ReservationRepository;
import com.nik.tripfinder.repositories.TripsRepository;
import com.nik.tripfinder.util.Timestamp;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TripsService {

    private final TripsRepository tripsRepository;
    private final AgenciesRepository agenciesRepository;
    private final ReservationRepository reservationRepository;
    private final TripDTOMapper tripDTOMapper;

    public TripsService(TripsRepository tripsRepository, AgenciesRepository agenciesRepository,
                        ReservationRepository reservationRepository,
                        TripDTOMapper tripDTOMapper) {
        this.tripsRepository = tripsRepository;
        this.agenciesRepository = agenciesRepository;
        this.reservationRepository = reservationRepository;
        this.tripDTOMapper = tripDTOMapper;
    }

    public TripDTO save(NewTripRequest trip) throws GeneralException {
        try {
            Long startDate = Timestamp.getMidnightTimestamp(trip.getTrip().getStartDate());
            Long endDate = Timestamp.getMidnightTimestamp(trip.getTrip().getEndDate());

            // Check if input timestamps are correct
            if (startDate > endDate) {
                throw new GeneralException("End date should be after start date", HttpStatus.BAD_REQUEST);
            }
            if (Timestamp.todaysTimestamp() > startDate) {
                throw new GeneralException("Start date cannot be before today", HttpStatus.BAD_REQUEST);
            }

            // Check if the given agency id corresponds to an agency
            Optional<Agency> agencyOptional = agenciesRepository.findById(trip.getAgencyId());
            if (!agencyOptional.isPresent()) {
                throw new GeneralException("There is no agency with id " + trip.getAgencyId(), HttpStatus.CONFLICT);
            }

            // Create new trip and return it
            Trip newTrip = new Trip(startDate, endDate,
                    trip.getTrip().getDepartureArea(),
                    trip.getTrip().getDestination(),
                    trip.getTrip().getTripSchedule(),
                    trip.getTrip().getMaxParticipants(),
                    agencyOptional.get());
            newTrip = tripsRepository.save(newTrip);

            return tripDTOMapper.apply(newTrip);
        } catch (Exception e) {
            if (e instanceof GeneralException)
                throw e;
            throw new GeneralException("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<TripDTO> findTripsWithOptionalParameters(
            Long startDate,
            Long endDate,
            String destination,
            String departureArea,
            Integer agencyId,
            Integer customerId) throws GeneralException {

        try{
            List<Trip> trips =
                    tripsRepository
                            .findTripsWithOptionalParameters(
                                    startDate,
                                    endDate,
                                    destination,
                                    departureArea,
                                    agencyId);

            trips.forEach(trip -> trip.setHasReservation(
                    trip.getReservations().stream().anyMatch(
                            reservation ->
                                    reservation.
                                            getCustomer().
                                            getCustomerId().equals(customerId))
            ));




            return tripDTOMapper.mapToDTOList(trips);
        }
        catch (Exception e){
            throw new GeneralException("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    public List<String> getAllDestinations() throws GeneralException {
        try {
            return tripsRepository.findAllDestinations();
        } catch (Exception e) {
            throw new GeneralException("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public List<String> getAllDepartureAreas() throws GeneralException {
        try {
            return tripsRepository.findAllDepartureAreas();
        } catch (Exception e) {
            throw new GeneralException("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public void deleteTrip(Long id) throws EntityNotFoundException, GeneralException {
        Optional<Trip> tripOptional = tripsRepository.findById(id);
        if (tripOptional.isPresent()) {
            List<Reservation> tripReservations = reservationRepository.findReservationsByTripId(id);
            for (Reservation reservation : tripReservations) {
                reservationRepository.delete(reservation);
            }
            Trip tripToDelete = tripOptional.get();
            tripsRepository.delete(tripToDelete);
        } else {
            throw new GeneralException("Entity with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

}
