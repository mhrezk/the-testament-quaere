package com.testament.veltahleon.services.ifc.religion.mythology;

import com.testament.veltahleon.model.entities.religion.mythology.Pantheon;

import java.util.Collection;

public interface PantheonService {

    Collection<Pantheon> getPantheonsWithPagination(int pageNumber, int numberOfRecords);
    Collection<Pantheon> getPantheons();
    Pantheon getPantheonByID(Long id);
    Boolean deletePantheonByID(Long id);
    Pantheon savePantheon(Pantheon pantheon);
    Pantheon updatePantheon(Long id, Pantheon pantheon);
}
