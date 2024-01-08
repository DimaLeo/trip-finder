package com.nik.tripfinder.services;

import com.nik.tripfinder.DTO.AgencyDTO.MinimalAgencyDTO;
import com.nik.tripfinder.DTO.AgencyDTO.MinimalAgencyDTOMapper;
import com.nik.tripfinder.DTO.TripDTO.TripDTO;
import com.nik.tripfinder.DTO.TripDTO.TripDTOMapper;
import com.nik.tripfinder.models.Agency;
import com.nik.tripfinder.models.Trip;
import com.nik.tripfinder.payloads.responses.AgencyTripsResponse;
import com.nik.tripfinder.payloads.responses.AllAgenciesResponse;
import com.nik.tripfinder.repositories.AgenciesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgenciesService {

    private final AgenciesRepository agenciesRepository;
    private  final MinimalAgencyDTOMapper minimalAgencyDTOMapper;
    private final TripDTOMapper tripDTOMapper;

    public AgenciesService(AgenciesRepository agenciesRepository, MinimalAgencyDTOMapper minimalAgencyDTOMapper, TripDTOMapper tripDTOMapper) {
        this.agenciesRepository = agenciesRepository;
        this.minimalAgencyDTOMapper = minimalAgencyDTOMapper;
        this.tripDTOMapper = tripDTOMapper;
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

    public AgencyTripsResponse findTrips(Integer user_id) {
        Optional<Agency> givenAgency = agenciesRepository.findAgencyByUserId(user_id);

        if (givenAgency.isPresent()) {
            List<Trip> trips = givenAgency.get().getTrips();
            List<TripDTO> listOfTrips = tripDTOMapper.mapToDTOList(trips);
            return new AgencyTripsResponse(
                    "SUCCESS",
                    "Trips successfully loaded",
                    givenAgency.get().getBrandName(),
                    givenAgency.get().getOwner(),
                    listOfTrips);

        } else {
            return new AgencyTripsResponse(
                    "FAILED",
                    "Failed to retrieve this agency from db.\n");
        }

    }
}
