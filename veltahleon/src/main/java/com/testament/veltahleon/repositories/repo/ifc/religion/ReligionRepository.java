package com.testament.veltahleon.repositories.repo.ifc.religion;

import com.testament.veltahleon.model.entities.religion.Religion;

import java.util.Collection;

public interface ReligionRepository {

    Collection<Religion> getReligions();
    Religion getReligionByID(Long id);
    Boolean deleteReligionByID(Long id);
    Religion saveReligion(Religion religion);
    Religion updateReligion(Religion religion);
}
