package com.nik.tripfinder.services;

import com.nik.tripfinder.repositories.TripsRepository;
import org.springframework.stereotype.Service;

@Service
public class TripsService {

    private final TripsRepository tripsRepository;

    public TripsService(TripsRepository tripsRepository) {
        this.tripsRepository = tripsRepository;
    }
}
