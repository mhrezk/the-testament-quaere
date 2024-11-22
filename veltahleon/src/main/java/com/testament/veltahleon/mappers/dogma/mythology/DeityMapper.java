package com.testament.veltahleon.mappers.dogma.mythology;

import com.testament.veltahleon.dto.dogma.mythology.DeityDTO;
import com.testament.veltahleon.facades.dogma.ReligionFacade;
import com.testament.veltahleon.model.entities.dogma.mythology.Deity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DeityMapper {

    @Autowired
    private ReligionFacade religionFacade;

    public DeityDTO convertToDTO(Deity deity) {
        return DeityDTO.builder()
                .id(deity.getId())
                .name(deity.getName())
                .description(deity.getDescription())
                .religion(deity.getReligion().getName())
                .imageURL(deity.getImageURL())
                .powerDomain(deity.getPowerDomain())
                .build();
    }

    public Deity convertToEntity(String religionName, DeityDTO deityDTO) {
        return Deity.builder()
                .id(deityDTO.getId())
                .name(deityDTO.getName())
                .imageURL(deityDTO.getImageURL())
                .description(deityDTO.getDescription())
                .powerDomain(deityDTO.getPowerDomain())
                .religion(religionFacade.getReligion(religionName))
                .build();
    }
}
