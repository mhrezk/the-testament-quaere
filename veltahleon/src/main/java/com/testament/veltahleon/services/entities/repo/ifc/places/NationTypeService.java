package com.testament.veltahleon.services.entities.repo.ifc.places;

import com.testament.veltahleon.model.entities.places.NationType;

import java.util.Collection;

public interface NationTypeService {

    Collection<NationType> getNationTypes();
    NationType getNationTypeByID(Long id);
    NationType getNationTypeByType(String type);
    Boolean deleteNationTypeByID(Long id);
    NationType saveNationType(NationType nationType);
    NationType updateNationType(Long id, NationType nationType);
}
