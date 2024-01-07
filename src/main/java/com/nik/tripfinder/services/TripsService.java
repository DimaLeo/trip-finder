package com.nik.tripfinder.services;

import com.nik.tripfinder.models.Trip;
import com.nik.tripfinder.repositories.TripsRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TripsService {

    private final TripsRepository tripsRepository;

    public TripsService(TripsRepository tripsRepository) {
        this.tripsRepository = tripsRepository;
    }

    public Trip save(Trip trip) {
        return tripsRepository.save(trip);
    }

    public List<Trip> findTripsWithOptionalParameters(
        Long startDate,
        Long endDate,
        String destination,
        String departurePoint) {
        return tripsRepository.findTripsWithOptionalParameters(startDate, endDate, destination, departurePoint);
    }

    public Trip getTripInfo(Long id) {
        return tripsRepository.findById(id).orElse(null);
    }

    public List<String> getAllDestinations() {
        return tripsRepository.findAllDestinations();
    }

    public List<String> getAllDeparturePoints() {
        return tripsRepository.findAllDeparturePoints();
    }
    
}
