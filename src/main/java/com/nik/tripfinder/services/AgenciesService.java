package com.nik.tripfinder.services;


import com.nik.tripfinder.DTO.AgencyDTO.AllAgenciesDTO;
import com.nik.tripfinder.DTO.AgencyDTO.AllAgenciesDTOMapper;
import com.nik.tripfinder.DTO.TripsOfAnAgencyDTO.TripsOfAnAgencyDTO;
import com.nik.tripfinder.models.Agency;
import com.nik.tripfinder.models.Trip;
import com.nik.tripfinder.payloads.responses.AllAgenciesResponse;
import com.nik.tripfinder.payloads.responses.TripsOfAnAgencyResponse;
import com.nik.tripfinder.repositories.AgenciesRepository;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgenciesService {

	@Autowired
    private final AgenciesRepository agenciesRepository;
	private final AllAgenciesDTOMapper allagenciesDTOMapper;
	//private final ModelMapper modelMapper;
	

    public AgenciesService(AgenciesRepository agenciesRepository, AllAgenciesDTOMapper allagenciesDTOMapper) {//, ModelMapper modelMapper) {
        this.agenciesRepository = agenciesRepository;
        this.allagenciesDTOMapper = allagenciesDTOMapper;
        //this.modelMapper = modelMapper;
    }
    

	public void addAgency(Agency a) throws Exception {
		Optional<Agency> byId = agenciesRepository.findById(a.getTaxCode());
		if(!byId.isPresent())
			agenciesRepository.save(a);
	}

	public AllAgenciesResponse getAllAgencies() {
		
		try {
			List<Agency> all_agencies = agenciesRepository.findAll();

			List<AllAgenciesDTO> listofagencies = allagenciesDTOMapper.mapToDTOList(all_agencies);
			return new AllAgenciesResponse(
                    "SUCCESS",
                    "Agencies successfully loaded",
                    listofagencies
            );
		}
		catch(Exception e) {
			return new AllAgenciesResponse(
                    "FAILED",
                    "Failed to retrieve agencies from db.\n" +
                            "message : "+ e.getMessage()
            );
		}
		
		
	}



	
	public TripsOfAnAgencyResponse findTrips(String taxcode) {
	    Optional<Agency> givenagency = agenciesRepository.findAgencyByTaxCode(taxcode);

	    if (givenagency.isPresent()) {
	    	//List<Trip> trips = givenagency.get().getTrips();
	    	return  new TripsOfAnAgencyResponse(
                    "SUCCESS",
                    "Trips successfully loaded",
                    givenagency.get().getTrips()//modelMapper.map(trips, TripsOfAnAgencyDTO.class)
            );
	    	
	        
	    } else {
	    	return new TripsOfAnAgencyResponse(
                    "FAILED",
                    "Failed to retrieve this agency from db.\n"
            );
	    }
	}
}
