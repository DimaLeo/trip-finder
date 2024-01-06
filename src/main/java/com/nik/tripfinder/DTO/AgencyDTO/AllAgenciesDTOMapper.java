package com.nik.tripfinder.DTO.AgencyDTO;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nik.tripfinder.models.Agency;

@Service
public class AllAgenciesDTOMapper {
    public List<AllAgenciesDTO> mapToDTOList(List<Agency> all_agencies) {
        return all_agencies.stream()
        	    .map(agency -> new AllAgenciesDTO(agency.getBrandName(), agency.getOwner()))
        	    .collect(Collectors.toList());
        
    }
    
}
