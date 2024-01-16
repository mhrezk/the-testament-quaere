package com.testament.veltahleon.repositories.repo.ifc.religion.mythology;

import com.testament.veltahleon.model.entities.religion.mythology.Fae;

import java.util.Collection;

public interface FaeRepository {

    Collection<Fae> getFaes();
    Fae getFaeByID(Long id);
    Boolean deleteFaeByID(Long id);
    Fae saveFae(Fae fae);
    Fae updateFae(Fae fae);
}
