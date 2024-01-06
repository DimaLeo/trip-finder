package com.nik.tripfinder.DTO.TripsOfAnAgencyDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nik.tripfinder.DTO.AgencyDTO.AllAgenciesDTO;
import com.nik.tripfinder.models.Agency;
import com.nik.tripfinder.models.Trip;
import com.nik.tripfinder.repositories.AgenciesRepository;

@Service
public class TripsOfAnAgencyDTOMapper {
	public AgenciesRepository agenciesRepository;
	//Optional<Agency> givenagency = agenciesRepository.findAgencyByTaxCode(taxcode);

	public void mapToDTOList(String taxcode) {
        //return givenagency.stream()
        	   // .map(agency -> new TripsOfAnAgencyDTO(agency.getBrandName(), agency.getOwner()))
        	    //.collect(Collectors.toList());
        
    }
}
