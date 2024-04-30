package com.testament.veltahleon.mappers.places;

import com.testament.veltahleon.dto.places.LandmarkDTO;
import com.testament.veltahleon.model.entities.places.Landmark;
import com.testament.veltahleon.services.ifc.places.NationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LandmarkMapper {

    @Autowired
    private NationService nationService;

    public LandmarkDTO convertToDTO(Landmark landmark) {
        return LandmarkDTO.builder()
                .id(landmark.getId())
                .name(landmark.getName())
                .description(landmark.getDescription())
                .nation(landmark.getNation().getName())
                .build();
    }

    public Landmark convertToEntity(LandmarkDTO landmarkDTO) {
        Landmark landmark = new Landmark();
        landmark.setName(landmark.getName());
        landmark.setDescription(landmark.getDescription());
        landmark.setNation(nationService.getNationByName(landmarkDTO.getNation()));
        return landmark;
    }
}
