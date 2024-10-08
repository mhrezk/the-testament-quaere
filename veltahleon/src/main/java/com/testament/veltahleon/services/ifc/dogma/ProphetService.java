package com.testament.veltahleon.services.ifc.dogma;

import com.testament.veltahleon.model.entities.dogma.Prophet;

import java.util.Collection;

public interface ProphetService {

    Collection<Prophet> getProphetsWithPagination(int pageNumber, int numberOfRecords);
    Collection<Prophet> getProphetsSortedWithPagination(int pageNumber, int numberOfRecords);
    Collection<Prophet> getProphets();
    Prophet getProphetByID(Long id);
    Prophet getProphetByName(String name);
    Boolean deleteProphetByID(Long id);
    Boolean deleteProphetByName(String name);
    Boolean deleteProphets();
    Prophet saveProphet(Prophet prophet);
    Collection<Prophet> saveProphets(Collection<Prophet> prophets);
    Prophet updateProphet(Long id, Prophet prophet);
}
