package com.testament.veltahleon.services.entities.repo.ifc.religion.mythology;

import com.testament.veltahleon.model.entities.religion.mythology.Demon;

import java.util.Collection;

public interface DemonService {

    Collection<Demon> getDemonsWithPagination(int pageNumber, int numberOfRecords);
    Collection<Demon> getDemons();
    Demon getDemonByID(Long id);
    Demon getDemonByName(String name);
    Boolean deleteDemonByID(Long id);
    Demon saveDemon(Demon demon);
    Demon updateDemon(Long id, Demon demon);
}
