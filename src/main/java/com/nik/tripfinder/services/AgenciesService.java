package com.nik.tripfinder.services;

import com.nik.tripfinder.DTO.AgencyDTO.AgencyDTO;
import com.nik.tripfinder.DTO.AgencyDTO.AgencyDTOMapper;
import com.nik.tripfinder.DTO.AgencyDTO.MinimalAgencyDTO;
import com.nik.tripfinder.DTO.AgencyDTO.MinimalAgencyDTOMapper;
import com.nik.tripfinder.DTO.TripDTO.TripDTO;
import com.nik.tripfinder.DTO.TripDTO.TripDTOMapper;
import com.nik.tripfinder.exceptions.GeneralException;
import com.nik.tripfinder.models.Agency;
import com.nik.tripfinder.repositories.AgenciesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgenciesService {

    private final AgenciesRepository agenciesRepository;
    private final MinimalAgencyDTOMapper minimalAgencyDTOMapper;
    private final TripDTOMapper tripDTOMapper;
    private final AgencyDTOMapper agencyDTOMapper;

    public AgenciesService(AgenciesRepository agenciesRepository, MinimalAgencyDTOMapper minimalAgencyDTOMapper,
                           TripDTOMapper tripDTOMapper, AgencyDTOMapper agencyDTOMapper) {
        this.agenciesRepository = agenciesRepository;
        this.minimalAgencyDTOMapper = minimalAgencyDTOMapper;
        this.tripDTOMapper = tripDTOMapper;
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

    public List<TripDTO> getAgencyTrips(Integer id) throws GeneralException {
        try {
            // Check if the given agency id corresponds to an agency 
            Optional<Agency> optionalAgency = this.agenciesRepository.findById(id);
            if (optionalAgency.isPresent()) {
                return tripDTOMapper.mapToDTOList(optionalAgency.get().getTrips());
            } else {
                throw new GeneralException("There is no agency with id " + id, HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            throw new GeneralException("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public AgencyDTO getAgencyDetails(Integer id) throws GeneralException {
        try {
            // Check if the given agency id corresponds to an agency 
            Optional<Agency> agency = agenciesRepository.findById(id);
            if (agency.isPresent()) {
                return agencyDTOMapper.apply(agency.get());
            } else {
                throw new GeneralException("There is no agency with id " + id, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new GeneralException("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
