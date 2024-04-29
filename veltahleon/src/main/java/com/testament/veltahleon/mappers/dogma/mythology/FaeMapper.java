package com.testament.veltahleon.mappers.dogma.mythology;

import com.testament.veltahleon.dto.dogma.mythology.FaeDTO;
import com.testament.veltahleon.model.entities.dogma.mythology.Fae;
import com.testament.veltahleon.services.ifc.history.RaceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FaeMapper {

    @Autowired
    private RaceService raceService;

    public FaeDTO convertToDTO(Fae fae) {
        return FaeDTO.builder()
                .id(fae.getId())
                .name(fae.getName())
                .description(fae.getDescription())
                .imageURL(fae.getImageURL())
                .race(fae.getRace().getName())
                .build();
    }

    public Fae convertToEntity(FaeDTO faeDTO) {
        Fae fae = new Fae();
        fae.setName(faeDTO.getName());
        fae.setDescription(faeDTO.getDescription());
        fae.setImageURL(faeDTO.getImageURL());
        fae.setRace(raceService.getRaceByName(faeDTO.getRace()));
        return fae;
    }
}
