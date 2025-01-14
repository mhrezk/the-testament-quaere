package com.testament.veltahleon.mappers.dogma.mythology;

import com.testament.veltahleon.dto.dogma.mythology.AngelDTO;
import com.testament.veltahleon.model.entities.dogma.mythology.Angel;
import com.testament.veltahleon.services.ifc.dogma.ReligionService;
import com.testament.veltahleon.services.ifc.history.RaceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AngelMapper {

    @Autowired
    private RaceService raceService;

    @Autowired
    private ReligionService religionService;

    public AngelDTO convertToDTO(Angel angel) {
        return AngelDTO.builder()
                .id(angel.getId())
                .name(angel.getName())
                .race(angel.getRace().getName())
                .religion(angel.getReligion().getName())
                .powerDomain(angel.getPowerDomain())
                .description(angel.getDescription())
                .imageURL(angel.getImageURL())
                .build();
    }

    public Angel convertToEntity(AngelDTO angelDTO) {
        return Angel.builder()
                .name(angelDTO.getName())
                .powerDomain(angelDTO.getPowerDomain())
                .description(angelDTO.getDescription())
                .imageURL(angelDTO.getImageURL())
                .race(raceService.getRaceByName(angelDTO.getRace()))
                .religion(religionService.getReligionByName(angelDTO.getReligion()))
                .build();
    }
}
