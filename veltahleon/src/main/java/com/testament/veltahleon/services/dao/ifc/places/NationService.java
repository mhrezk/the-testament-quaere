package com.testament.veltahleon.services.dao.ifc.places;

import com.testament.veltahleon.model.entities.places.Nation;

import java.util.Collection;

public interface NationService {

    Collection<Nation> getNations();
    Nation getNationByID(Long id);
    Boolean deleteNationByID(Long id);
    Nation saveNation(Nation nation);
    Nation updateNation(Nation nation);
}
