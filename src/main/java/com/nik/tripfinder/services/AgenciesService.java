package com.nik.tripfinder.services;

import com.nik.tripfinder.DTO.AgencyDTO.MinimalAgencyDTO;
import com.nik.tripfinder.DTO.AgencyDTO.MinimalAgencyDTOMapper;
import com.nik.tripfinder.DTO.TripDTO.TripDTOMapper;
import com.nik.tripfinder.models.Agency;
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

    public List<MinimalAgencyDTO> getAllAgencies() throws Exception {
        try {
            List<Agency> agencies = agenciesRepository.findAll();

            return minimalAgencyDTOMapper.mapToDTOList(agencies);
        } catch (Exception e) {
            throw new Exception("Failed to retrieve agencies from db.");
        }

    }
}
