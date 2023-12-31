package com.nik.tripfinder.services;


import com.nik.tripfinder.models.Agency;
import com.nik.tripfinder.models.Trip;
import com.nik.tripfinder.repositories.AgenciesRepository;

import jakarta.transaction.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgenciesService {

	@Autowired
    private final AgenciesRepository agenciesRepository;
	

    public AgenciesService(AgenciesRepository agenciesRepository) {
        this.agenciesRepository = agenciesRepository;
    }
    

	public void addAgency(Agency a) throws Exception {
		Optional<Agency> byId = agenciesRepository.findById(a.getTaxCode());
		if(!byId.isPresent())
			agenciesRepository.save(a);
	}

	public List<String> getAllAgencies() throws Exception {
		return agenciesRepository.findAll().stream().map(Agency::getBrandName).collect(Collectors.toList());
	}



	@Transactional
	public List<Trip> findTrips(String taxcode) throws Exception {
	    Optional<Agency> givenagency = agenciesRepository.findByTaxCode(taxcode);

	    if (givenagency.isPresent()) {
	        return givenagency.get().getTrips();
	    } else {
	        return Collections.emptyList();
	    }
	}
}
