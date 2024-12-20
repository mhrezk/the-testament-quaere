package com.testament.veltahleon.services.ifc.dogma;

import com.testament.veltahleon.model.entities.dogma.Religion;

import java.util.Collection;

public interface ReligionService {

    Collection<Religion> getReligionsWithPagination(int pageNumber, int numberOfRecords);
    Collection<Religion> getReligions();
    Religion getReligionByID(Long id);
    Religion getReligionByName(String name);
    Boolean deleteReligionByID(Long id);
    Religion removeNationFromReligion(Long religionId, String nationName);
    Religion saveReligion(Religion religion);
    Religion updateReligion(Long id, Religion religion);
    Religion modifyReligion(Long id, Religion religion);
    long countReligions();
}
