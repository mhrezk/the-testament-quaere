package com.testament.veltahleon.services.ifc.places;

import com.testament.veltahleon.model.entities.places.Nation;

import java.util.Collection;

public interface NationService {

    Collection<Nation> getNationsWithPagination(int pageNumber, int numberOfRecords);
    Collection<Nation> getNations();
    Collection<Nation> getNationsByNationType(String nationType);
    Collection<Nation> getNationsByGovernanceType(String governanceType);
    Nation getNationByID(Long id);
    Nation getNationByName(String name);
    Boolean deleteNationByID(Long id);
    Nation saveNation(Nation nation);
    Nation updateNation(Long id, Nation nation);
    Nation modifyNation(Long id, Nation nation);
}
