package com.testament.veltahleon.repositories.dao.ifc.places;

import com.testament.veltahleon.model.entities.places.NationType;

import java.util.Collection;

public interface NationTypeDAO {

    Collection<NationType> getNationTypes();
    NationType getNationTypeByID(Long id);
    Boolean deleteNationTypeByID(Long id);
    NationType saveNationType(NationType nationType);
    NationType updateNationType(NationType nationType);
}
