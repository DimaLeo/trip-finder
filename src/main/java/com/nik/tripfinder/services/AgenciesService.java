package com.nik.tripfinder.services;

import com.nik.tripfinder.DTO.AgencyDTO.MinimalAgencyDTO;
import com.nik.tripfinder.DTO.AgencyDTO.MinimalAgencyDTOMapper;
import com.nik.tripfinder.DTO.TripDTO.TripDTOMapper;
import com.nik.tripfinder.models.Agency;
import com.nik.tripfinder.payloads.responses.AllAgenciesResponse;
import com.nik.tripfinder.repositories.AgenciesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgenciesService {

    private final AgenciesRepository agenciesRepository;
    private  final MinimalAgencyDTOMapper minimalAgencyDTOMapper;

    public AgenciesService(AgenciesRepository agenciesRepository, MinimalAgencyDTOMapper minimalAgencyDTOMapper, TripDTOMapper tripDTOMapper) {
        this.agenciesRepository = agenciesRepository;
        this.minimalAgencyDTOMapper = minimalAgencyDTOMapper;
    }

    public AllAgenciesResponse getAllAgencies() {

        try {
            List<Agency> allAgencies = agenciesRepository.findAll();

            List<MinimalAgencyDTO> listOfAgencies = minimalAgencyDTOMapper.mapToDTOList(allAgencies);
            return new AllAgenciesResponse(
                    "SUCCESS",
                    "Agencies successfully loaded",
                    listOfAgencies);
        } catch (Exception e) {
            return new AllAgenciesResponse(
                    "FAILED",
                    "Failed to retrieve agencies from db.\n" +
                            "message : " + e.getMessage());
        }

    }
}
