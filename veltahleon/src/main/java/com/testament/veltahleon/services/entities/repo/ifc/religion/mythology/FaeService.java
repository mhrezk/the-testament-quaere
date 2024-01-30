package com.testament.veltahleon.services.entities.repo.ifc.religion.mythology;

import com.testament.veltahleon.model.entities.religion.mythology.Fae;

import java.util.Collection;

public interface FaeService {

    Collection<Fae> getFaesWithPagination(int pageNumber, int numberOfRecords);
    Collection<Fae> getFaes();
    Fae getFaeByID(Long id);
    Fae getFaeByName(String name);
    Boolean deleteFaeByID(Long id);
    Fae saveFae(Fae fae);
    Fae updateFae(Fae fae);
}
