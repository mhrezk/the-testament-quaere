package com.testament.veltahleon.repositories.dao.ifc.religion;

import com.testament.veltahleon.model.entities.religion.Pantheon;

import java.util.Collection;

public interface PantheonDAO {

    Collection<Pantheon> getPantheons();
    Pantheon getPantheonByID(Long id);
    Boolean deletePantheonByID(Long id);
    Pantheon savePantheon(Pantheon pantheon);
    Pantheon updatePantheon(Pantheon pantheon);
}
