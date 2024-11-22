package com.testament.veltahleon.services.ifc.dogma.mythology;

import com.testament.veltahleon.model.entities.dogma.mythology.Deity;

import java.util.Collection;

public interface DeityService {
    Collection<Deity> getDeitiesWithPaginationByReligionName(String name, int pageNumber, int numberOfRecords);
    Collection<Deity> getDeitiesByReligionName(String name);
    Collection<Deity> getDeitiesWithPagination(int pageNumber, int numberOfRecords);
    Collection<Deity> getDeities();
    Deity getDeityByID(Long id);
    Deity getDeityByName(String name);
    Boolean deleteDeityByID(Long id);
    Deity saveDeity(Deity deity, String religionName);
    Deity updateDeity(Long id, Deity deity);
    Deity modifyDeity(Long id, Deity deity);
    long countDeitiesByReligionName(String name);
}
