package com.testament.veltahleon.services.entities.repo.ifc.religion.mythology;

import com.testament.veltahleon.model.entities.religion.mythology.Angel;

import java.util.Collection;

public interface AngelService {

    Collection<Angel> getAngelsWithPagination(int pageNumber, int numberOfRecords);
    Collection<Angel> getAngels();
    Angel getAngelByID(Long id);
    Angel getAngelByName(String name);
    Boolean deleteAngelByID(Long id);
    Angel saveAngel(Angel angel);
    Angel updateAngel(Angel angel);
}
