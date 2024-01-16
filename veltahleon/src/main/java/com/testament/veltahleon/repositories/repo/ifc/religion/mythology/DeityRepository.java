package com.testament.veltahleon.repositories.repo.ifc.religion.mythology;

import com.testament.veltahleon.model.entities.religion.mythology.Deity;

import java.util.Collection;

public interface DeityRepository {

    Collection<Deity> getDeities();
    Deity getDeityByID(Long id);
    Boolean deleteDeityByID(Long id);
    Deity saveDeity(Deity deity);
    Deity updateDeity(Deity deity);
}
