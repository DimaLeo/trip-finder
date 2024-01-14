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

    public TripsService(TripsRepository tripsRepository, AgenciesRepository agenciesRepository, ReservationRepository reservationRepository,
                        TripDTOMapper tripDTOMapper) {
        this.tripsRepository = tripsRepository;
        this.agenciesRepository = agenciesRepository;
        this.reservationRepository = reservationRepository;
        this.tripDTOMapper = tripDTOMapper;
    }

    public TripDTO save(NewTripRequest trip) throws GeneralException {

        System.err.println(trip.getTrip().getEndDate());
        System.err.println(trip.getAgencyId());
        // System.err.println();
        return null;

        // Agency dbAgency;

        // try {
        //     Optional<Agency> agencyOptional = agenciesRepository.findById(trip.getAgencyId());

        //     if (!agencyOptional.isPresent()) {
        //         throw new GeneralException("Failed to retrieve the agency.", HttpStatus.CONFLICT);
        //     }

        //     dbAgency = agencyOptional.get();
        // } catch (Exception e) {
        //     if(e instanceof GeneralException && ((GeneralException) e).getStatus().equals(HttpStatus.CONFLICT)){
        //         throw e;
        //     }
        //     throw new GeneralException("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        // }

        // try {
        //     Trip newTrip = new Trip(
        //             trip.getStartDate(),
        //             trip.getEndDate(),
        //             trip.getDepartureArea(),
        //             trip.getDestination(),
        //             trip.getTripSchedule(),
        //             trip.getMaxParticipants(),
        //             dbAgency);

        //     newTrip = tripsRepository.save(newTrip);

        //     return tripDTOMapper.apply(newTrip);

        // } catch (Exception e) {
        //     throw new GeneralException("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        // }
    }

    public List<TripDTO> findTripsWithOptionalParameters(
            Long startDate,
            Long endDate,
            String destination,
            String departureArea,
            Integer agencyId,
            Integer customerId) throws GeneralException {

        try{
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
                trip.setCurrentParticipants(reservationRepository.countByTripId(trip.getId()));
                if (!reservations.isEmpty()) {
                    for (Reservation r: reservations) {
                        if (r.getCustomer().getCustomerId() == customerId && r.getTrip().getId() == trip.getId()) {
                            trip.setReservationId(r.getReservationId());
                            reservations.remove(r);
                            break;
                        }
                    }
                }
            }

            return trips;
        }
        catch (Exception e){
            throw new GeneralException("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public List<String> getAllDestinations() throws GeneralException {
        try{
            return tripsRepository.findAllDestinations();
        }
        catch (Exception e){
            throw new GeneralException("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public List<String> getAllDepartureAreas() throws GeneralException {
        try{
            return tripsRepository.findAllDepartureAreas();
        }
        catch (Exception e){
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
