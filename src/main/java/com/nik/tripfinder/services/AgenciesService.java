package com.nik.tripfinder.services;

import com.nik.tripfinder.DTO.AgencyDTO.AgencyDTO;
import com.nik.tripfinder.DTO.AgencyDTO.AgencyDTOMapper;
import com.nik.tripfinder.DTO.AgencyDTO.MinimalAgencyDTO;
import com.nik.tripfinder.DTO.AgencyDTO.MinimalAgencyDTOMapper;
import com.nik.tripfinder.DTO.TripDTO.TripDTOMapper;
import com.nik.tripfinder.models.Agency;
import com.nik.tripfinder.repositories.AgenciesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgenciesService {

    private final AgenciesRepository agenciesRepository;
    private final MinimalAgencyDTOMapper minimalAgencyDTOMapper;
    private final AgencyDTOMapper agencyDTOMapper;

    public AgenciesService(AgenciesRepository agenciesRepository, MinimalAgencyDTOMapper minimalAgencyDTOMapper,
            TripDTOMapper tripDTOMapper, AgencyDTOMapper agencyDTOMapper) {
        this.agenciesRepository = agenciesRepository;
        this.minimalAgencyDTOMapper = minimalAgencyDTOMapper;
        this.agencyDTOMapper = agencyDTOMapper;
    }

    public List<MinimalAgencyDTO> getAllAgencies() throws Exception {
        try {
            List<Agency> agencies = agenciesRepository.findAll();

            return minimalAgencyDTOMapper.mapToDTOList(agencies);
        } catch (Exception e) {
            throw new Exception("Failed to retrieve agencies from db.");
        }

    }

    public AgencyDTO getAgencyDetails(Integer id) throws Exception {
        try {
            Optional<Agency> agency = agenciesRepository.findById(id);
            if (agency.isPresent()) {
                return agencyDTOMapper.apply(agency.get());
            } else {
                throw new Exception("Failed to retrieve agency from db.");
            }
        } catch (Exception e) {
            throw new Exception("Failed to retrieve profile details from db.");
        }

    }
}
