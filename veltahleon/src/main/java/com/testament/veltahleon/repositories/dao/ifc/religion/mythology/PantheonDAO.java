package com.testament.veltahleon.repositories.dao.ifc.religion.mythology;

import com.testament.veltahleon.model.entities.religion.mythology.Pantheon;

import java.util.Collection;

public interface PantheonDAO {

    Collection<Pantheon> getPantheons();
    Pantheon getPantheonByID(Long id);
    Boolean deletePantheonByID(Long id);
    Pantheon savePantheon(Pantheon pantheon);
    Pantheon updatePantheon(Pantheon pantheon);
}
