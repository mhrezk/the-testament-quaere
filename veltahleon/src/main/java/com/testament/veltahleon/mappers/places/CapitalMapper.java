package com.testament.veltahleon.mappers.places;

import com.testament.veltahleon.dto.places.CapitalDTO;
import com.testament.veltahleon.model.entities.places.Capital;
import com.testament.veltahleon.services.ifc.places.NationDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CapitalMapper {

    @Autowired
    private NationDetailsService nationDetailsService;

    public CapitalDTO convertToDTO(Capital capital) {
        return CapitalDTO.builder()
                .id(capital.getId())
                .name(capital.getName())
                .history(capital.getHistory())
                //.nation(capital.getNation().getName())
                .flagURL(capital.getFlagURL())
                .build();
    }

    public Capital convertToEntity(CapitalDTO capitalDTO) {
        Capital capital = new Capital();
        capital.setId(capitalDTO.getId());
        capital.setName(capitalDTO.getName());
        capital.setHistory(capitalDTO.getHistory());
        capital.setFlagURL(capitalDTO.getFlagURL());
        //capital.setNation(nationDetailsService.getNationDetailsByNationName(capitalDTO.getName()));
        return capital;
    }
}
