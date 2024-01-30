package com.testament.veltahleon.services.entities.repo.ifc.religion.mythology;

import com.testament.veltahleon.model.entities.religion.mythology.Deity;

import java.util.Collection;

public interface DeityService {

    Collection<Deity> getDeitiesWithPagination(int pageNumber, int numberOfRecords);
    Collection<Deity> getDeities();
    Deity getDeityByID(Long id);
    Deity getDeityByName(String name);
    Boolean deleteDeityByID(Long id);
    Deity saveDeity(Deity deity);
    Deity updateDeity(Long id, Deity deity);
}
