package com.testament.veltahleon.mappers.dogma;

import com.testament.veltahleon.dto.dogma.ReligionDTO;
import com.testament.veltahleon.facades.places.NationFacade;
import com.testament.veltahleon.model.entities.dogma.Religion;
import com.testament.veltahleon.model.entities.dogma.mythology.Deity;
import com.testament.veltahleon.model.entities.places.Nation;
import com.testament.veltahleon.services.ifc.dogma.mythology.DeityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReligionMapper {

    @Autowired
    private NationFacade nationFacade;

    public ReligionDTO convertToDTO(Religion religion) {
        return ReligionDTO.builder()
                .id(religion.getId())
                .name(religion.getName())
                .description(religion.getDescription())
                .symbolURL(religion.getSymbolURL())
                .nations(religion.getNations().stream().map(Nation::getName).toList())
                .build();
    }

    public Religion convertToEntity(ReligionDTO religionDTO) {
        Religion religion = new Religion();
        religion.setId(religionDTO.getId());
        religion.setName(religionDTO.getName());
        religion.setDescription(religionDTO.getDescription());
        religion.setSymbolURL(religionDTO.getSymbolURL());
        System.out.println(religionDTO.getSymbolURL());
        religion.setNations(nationFacade.getNations(religionDTO.getNations()));
        return religion;
    }
}
