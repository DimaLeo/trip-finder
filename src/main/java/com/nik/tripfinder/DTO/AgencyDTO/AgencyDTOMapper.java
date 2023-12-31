package com.nik.tripfinder.DTO.AgencyDTO;

import com.nik.tripfinder.models.Agency;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AgencyDTOMapper implements Function<Agency, AgencyDTO> {
    @Override
    public AgencyDTO apply(Agency agency) {
        return new AgencyDTO(
                agency.getUser().getUsername(),
                agency.getUser().getUserType(),
                agency.getTaxCode(),
                agency.getBrandName(),
                agency.getOwner());
    }
}
