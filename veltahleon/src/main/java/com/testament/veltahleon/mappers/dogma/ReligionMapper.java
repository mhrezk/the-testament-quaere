package com.testament.veltahleon.mappers.dogma;

import com.testament.veltahleon.dto.dogma.ReligionDTO;
import com.testament.veltahleon.model.entities.dogma.Religion;
import com.testament.veltahleon.model.entities.dogma.mythology.Deity;
import com.testament.veltahleon.services.ifc.religion.mythology.DeityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReligionMapper {

    @Autowired
    private DeityService deityService;

    public ReligionDTO convertToDTO(Religion religion) {
        return ReligionDTO.builder()
                .id(religion.getId())
                .name(religion.getName())
                .description(religion.getDescription())
                .deities(religion.getDeities().stream().map(Deity::getName).toList())
                .build();
    }

    public Religion convertToEntity(ReligionDTO religionDTO) {
        Religion religion = new Religion();
        religion.setName(religionDTO.getName());
        religion.setDescription(religionDTO.getDescription());
        religion.setDeities(religionDTO.getDeities().stream().map(r -> deityService.getDeityByName(r)).toList());
        return religion;
    }
}
