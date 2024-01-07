package com.nik.tripfinder.services;

import com.nik.tripfinder.DTO.AgencyDTO.AllAgenciesDTO;
import com.nik.tripfinder.DTO.AgencyDTO.AllAgenciesDTOMapper;
import com.nik.tripfinder.DTO.TripsOfAnAgencyDTO.TripsOfAnAgencyDTO;
import com.nik.tripfinder.DTO.TripsOfAnAgencyDTO.TripsOfAnAgencyDTOMapper;
import com.nik.tripfinder.models.Agency;
import com.nik.tripfinder.models.Trip;
import com.nik.tripfinder.payloads.responses.AllAgenciesResponse;
import com.nik.tripfinder.payloads.responses.TripsOfAnAgencyResponse;
import com.nik.tripfinder.repositories.AgenciesRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class AgenciesService {

    private final AgenciesRepository agenciesRepository;
    private final AllAgenciesDTOMapper allagenciesDTOMapper;

    public AgenciesService(AgenciesRepository agenciesRepository, AllAgenciesDTOMapper allagenciesDTOMapper) {
        this.agenciesRepository = agenciesRepository;
        this.allagenciesDTOMapper = allagenciesDTOMapper;
    }

    public AllAgenciesResponse getAllAgencies() {

        try {
            List<Agency> all_agencies = agenciesRepository.findAll();

            List<AllAgenciesDTO> listofagencies = allagenciesDTOMapper.mapToDTOList(all_agencies);
            return new AllAgenciesResponse(
                    "SUCCESS",
                    "Agencies successfully loaded",
                    listofagencies);
        } catch (Exception e) {
            return new AllAgenciesResponse(
                    "FAILED",
                    "Failed to retrieve agencies from db.\n" +
                            "message : " + e.getMessage());
        }

    }

    public TripsOfAnAgencyResponse findTrips(String taxcode) {
        Optional<Agency> givenagency = agenciesRepository.findAgencyByTaxCode(taxcode);

        if (givenagency.isPresent()) {
            List<Trip> trips = givenagency.get().getTrips();
            List<TripsOfAnAgencyDTO> listofagencies = TripsOfAnAgencyDTOMapper.mapToDTOList(trips);
            return new TripsOfAnAgencyResponse(
                    "SUCCESS",
                    "Trips successfully loaded",
                    listofagencies);

        } else {
            return new TripsOfAnAgencyResponse(
                    "FAILED",
                    "Failed to retrieve this agency from db.\n");
        }
    }
}
