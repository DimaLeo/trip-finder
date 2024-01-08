package com.nik.tripfinder.DTO.AgencyDTO;

import com.nik.tripfinder.models.Agency;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MinimalAgencyDTOMapper implements Function<Agency, MinimalAgencyDTO> {
    @Override
    public MinimalAgencyDTO apply(Agency agency) {
        return new MinimalAgencyDTO(
                agency.getBrandName(),
                agency.getOwner()
        );
    }

    public List<MinimalAgencyDTO> mapToDTOList(List<Agency> all_agencies) {
        return all_agencies.stream()
                .map(agency -> new MinimalAgencyDTO(agency.getBrandName(), agency.getOwner()))
                .collect(Collectors.toList());

    }
}
