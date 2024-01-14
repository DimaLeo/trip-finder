package com.nik.tripfinder.services;

import com.nik.tripfinder.DTO.CustomerDTO.CustomerDTO;
import com.nik.tripfinder.DTO.CustomerDTO.CustomerDTOMapper;
import com.nik.tripfinder.DTO.TripDTO.TripDTO;
import com.nik.tripfinder.DTO.TripDTO.TripDTOMapper;
import com.nik.tripfinder.exceptions.GeneralException;
import com.nik.tripfinder.models.Agency;
import com.nik.tripfinder.models.Reservation;
import com.nik.tripfinder.models.Trip;
import com.nik.tripfinder.payloads.requests.NewTripRequest;
import com.nik.tripfinder.payloads.responses.TripReservationsResponse;
import com.nik.tripfinder.repositories.AgenciesRepository;
import com.nik.tripfinder.repositories.TripsRepository;
import com.nik.tripfinder.util.Timestamp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TripsService {

    private final TripsRepository tripsRepository;
    private final AgenciesRepository agenciesRepository;
    private final TripDTOMapper tripDTOMapper;
    private final CustomerDTOMapper customerDTOMapper;

    public TripsService(TripsRepository tripsRepository, AgenciesRepository agenciesRepository,
                        TripDTOMapper tripDTOMapper, CustomerDTOMapper customerDTOMapper) {
        this.tripsRepository = tripsRepository;
        this.agenciesRepository = agenciesRepository;
        this.tripDTOMapper = tripDTOMapper;
        this.customerDTOMapper = customerDTOMapper;

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

        try {
            // Get the trips with the given filters (all are optional) 
            // It does not return trips with start date before today
            List<Trip> trips = tripsRepository.findTripsWithOptionalParameters(
                                    startDate, endDate,
                                    destination, departureArea,
                                    agencyId, Timestamp.todaysTimestamp());

            
            if (customerId != null) {
                // Add a field to each trip indicating if the given user has a reservation for it
                trips.forEach(trip -> trip.setHasReservation(
                    trip.getReservations().stream().anyMatch(
                            reservation -> reservation.getCustomer().getCustomerId().equals(customerId))
                ));
            }

            return tripDTOMapper.mapToDTOList(trips);
        } catch (Exception e) {
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

    public void deleteTrip(Long id) throws GeneralException {
        Optional<Trip> tripOptional = tripsRepository.findById(id);
        if (tripOptional.isPresent()) {
            tripsRepository.delete(tripOptional.get());
        } else {
            throw new GeneralException("There is not trip with id" + id, HttpStatus.NOT_FOUND);
        }
    }

    public TripReservationsResponse getTripReservations(Long tripId) throws GeneralException {

        try {
            Optional<Trip> optionalTrip = tripsRepository.findById(tripId);

            if(optionalTrip.isPresent()){
                List<Integer> listOfId = new ArrayList<>();
                List<CustomerDTO> customers = optionalTrip.get().getReservations()
                        .stream()
                        .map(reservation -> customerDTOMapper.apply(reservation.getCustomer())).toList();

                for(Reservation r: optionalTrip.get().getReservations()){
                    listOfId.add(r.getReservationId());
                }

                return new TripReservationsResponse(
                        "SUCCESS",
                        "Reservations successfully retrieved",
                        listOfId,
                        customers);
            }
            else throw new Exception();

        }
        catch (Exception e){
            throw new GeneralException("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }


}
