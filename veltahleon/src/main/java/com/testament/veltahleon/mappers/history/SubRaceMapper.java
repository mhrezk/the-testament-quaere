package com.testament.veltahleon.mappers.history;

import com.testament.veltahleon.dto.history.SubRaceDTO;
import com.testament.veltahleon.model.entities.history.SubRace;
import com.testament.veltahleon.services.ifc.history.RaceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SubRaceMapper {

    @Autowired
    private RaceService raceService;

    public SubRaceDTO convertToDTO(SubRace subRace) {
        return SubRaceDTO.builder()
                .id(subRace.getId())
                .name(subRace.getName())
                .description(subRace.getDescription())
                .race(subRace.getRace().getName())
                .imageURL(subRace.getImageURL())
                .build();
    }

    public SubRace convertToEntity(SubRaceDTO subRaceDTO, String raceName) {
        SubRace subRace = new SubRace();
        subRace.setId(subRaceDTO.getId());
        subRace.setName(subRaceDTO.getName());
        subRace.setDescription(subRaceDTO.getDescription());
        subRace.setRace(raceService.getRaceByName(raceName));
        subRace.setImageURL(subRaceDTO.getImageURL());
        return subRace;
    }
}
