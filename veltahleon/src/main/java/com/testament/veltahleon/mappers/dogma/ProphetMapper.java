package com.testament.veltahleon.mappers.dogma;

import com.testament.veltahleon.dto.dogma.ProphetDTO;
import com.testament.veltahleon.facades.places.NationFacade;
import com.testament.veltahleon.model.entities.dogma.Prophet;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProphetMapper {

    @Autowired
    private NationFacade nationFacade;

    public ProphetDTO convertToDTO(Prophet prophet) {
        return ProphetDTO.builder()
                .id(prophet.getId())
                .name(prophet.getName())
                .description(prophet.getDescription())
                .religion(prophet.getReligion().getName())
                .build();
    }

    public Prophet convertToEntity(ProphetDTO prophetDTO) {
        return Prophet.builder()
                .id(prophetDTO.getId())
                .name(prophetDTO.getName())
                .description(prophetDTO.getDescription())
                .religion(nationFacade.getReligion(prophetDTO.getReligion()))
                .build();
    }
}
