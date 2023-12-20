package com.nik.tripfinder.services;

import com.nik.tripfinder.repositories.AgenciesRepository;
import org.springframework.stereotype.Service;

@Service
public class AgenciesService {

    private final AgenciesRepository agenciesRepository;

    public AgenciesService(AgenciesRepository agenciesRepository) {
        this.agenciesRepository = agenciesRepository;
    }
}
