package com.testament.veltahleon.mappers.dogma.mythology;

import com.testament.veltahleon.dto.dogma.mythology.DemonDTO;
import com.testament.veltahleon.model.entities.dogma.mythology.Demon;
import com.testament.veltahleon.services.ifc.history.RaceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DemonMapper {

    @Autowired
    private RaceService raceService;

    public DemonDTO convertToDTO(Demon demon) {
        return DemonDTO.builder()
                .id(demon.getId())
                .name(demon.getName())
                .race(demon.getRace().getName())
                .description(demon.getDescription())
                .imageURL(demon.getImageURL())
                .build();
    }

    public Demon convertToEntity(DemonDTO demonDTO) {
        Demon demon = new Demon();
        demon.setName(demonDTO.getName());
        demon.setDescription(demonDTO.getDescription());
        demon.setImageURL(demonDTO.getImageURL());
        demon.setRace(raceService.getRaceByName(demonDTO.getName()));
        return demon;
    }
}
