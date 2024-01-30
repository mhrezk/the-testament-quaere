package com.testament.veltahleon.services.entities.repo.ifc.religion;

import com.testament.veltahleon.model.entities.religion.Religion;

import java.util.Collection;

public interface ReligionService {

    Collection<Religion> getReligionsWithPagination(int pageNumber, int numberOfRecords);
    Collection<Religion> getReligions();
    Religion getReligionByID(Long id);
    Religion getReligionByName(String name);
    Boolean deleteReligionByID(Long id);
    Religion saveReligion(Religion religion);
    Religion updateReligion(Long id, Religion religion);
}
