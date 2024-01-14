package com.nik.tripfinder.services;

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

    public AgenciesService(AgenciesRepository agenciesRepository, MinimalAgencyDTOMapper minimalAgencyDTOMapper,
                           TripDTOMapper tripDTOMapper) {
        this.agenciesRepository = agenciesRepository;
        this.minimalAgencyDTOMapper = minimalAgencyDTOMapper;
        this.tripDTOMapper = tripDTOMapper;
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
        Agency dbAgency;

        try {
            Optional<Agency> optionalAgency = this.agenciesRepository.findById(id);

            if (optionalAgency.isPresent()) {
                dbAgency = optionalAgency.get();
                return tripDTOMapper.mapToDTOList(dbAgency.getTrips());
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new GeneralException("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
