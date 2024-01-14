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
        return new MinimalAgencyDTO(agency.getId(), agency.getBrandName());
    }

    public List<MinimalAgencyDTO> mapToDTOList(List<Agency> agencies) {
        return agencies.stream().map(agency -> apply(agency)).collect(Collectors.toList());
    }
}
