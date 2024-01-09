package com.nik.tripfinder.services;

import com.nik.tripfinder.DTO.TripDTO.TripDTO;
import com.nik.tripfinder.DTO.TripDTO.TripDTOMapper;
import com.nik.tripfinder.models.Agency;
import com.nik.tripfinder.models.Trip;
import com.nik.tripfinder.payloads.requests.NewTripRequest;
import com.nik.tripfinder.payloads.responses.NewTripResponse;
import com.nik.tripfinder.repositories.AgenciesRepository;
import com.nik.tripfinder.repositories.TripsRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class TripsService {

    private final TripsRepository tripsRepository;
    private final AgenciesRepository agenciesRepository;
    private final TripDTOMapper tripDTOMapper;

    public TripsService(TripsRepository tripsRepository, AgenciesRepository agenciesRepository, TripDTOMapper tripDTOMapper) {
        this.tripsRepository = tripsRepository;
        this.agenciesRepository = agenciesRepository;
        this.tripDTOMapper = tripDTOMapper;
    }

    public NewTripResponse save(NewTripRequest trip) {

        Agency dbAgency;

        try {
            Optional<Agency> agencyOptional = agenciesRepository.findAgencyByUserId(trip.getUserId());

            if (!agencyOptional.isPresent()) {
                return new NewTripResponse(
                        "FAILED",
                        "Failed to retrieve the agency from db."
                );
            }

            dbAgency = agencyOptional.get();
        } catch (Exception e) {
            return new NewTripResponse(
                    "FAILED",
                    "Failed while trying to retrieve agency info.\n"+
                            "message : "+ e.getMessage()
            );
        }

        try {
            Trip newTrip = new Trip(
                    trip.getTrip().id(),
                    trip.getTrip().startDate(),
                    trip.getTrip().endDate(),
                    trip.getTrip().departureArea(),
                    trip.getTrip().destination(),
                    trip.getTrip().tripSchedule(),
                    trip.getTrip().maxParticipants(),
                    dbAgency
            );

            newTrip = tripsRepository.save(newTrip);

            return new NewTripResponse(
                    "SUCCESS",
                    "Trip successfully saved",
                    tripDTOMapper.apply(newTrip)
            );

        }
        catch (Exception e) {
            return new NewTripResponse(
                    "FAILED",
                    "Failed to save new trip.\n"+
                            "message : "+ e.getMessage()
            );
        }
    }

    public List<TripDTO> findTripsWithOptionalParameters(
            Long startDate,
            Long endDate,
            String destination,
            String departureArea) {
        List<Trip> trips = tripsRepository.findTripsWithOptionalParameters(startDate, endDate, destination, departureArea);
        return tripDTOMapper.mapToDTOList(trips);
    }

    public Trip getTripInfo(Long id) {
        return tripsRepository.findById(id).orElse(null);
    }

    public List<String> getAllDestinations() {
        return tripsRepository.findAllDestinations();
    }

    public List<String> getAllDepartureAreas() {
        return tripsRepository.findAllDepartureAreas();
    }

}
