package com.testament.veltahleon.mappers.places;

import com.testament.veltahleon.dto.places.LocationDTO;
import com.testament.veltahleon.model.entities.places.Location;
import com.testament.veltahleon.services.ifc.places.NationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LocationMapper {

    @Autowired
    private NationService nationService;

    public LocationDTO convertToDTO(Location location) {
        return LocationDTO.builder()
                .id(location.getId())
                .name(location.getName())
                .description(location.getDescription())
                .nation(location.getNation().getName())
                .build();
    }

    public Location convertToEntity(LocationDTO locationDTO) {
        Location location = new Location();
        location.setName(locationDTO.getName());
        location.setDescription(locationDTO.getDescription());
        location.setNation(nationService.getNationByName(locationDTO.getNation()));
        return location;
    }
}
