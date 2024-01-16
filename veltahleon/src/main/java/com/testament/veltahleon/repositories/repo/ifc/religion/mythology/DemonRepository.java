package com.testament.veltahleon.repositories.repo.ifc.religion.mythology;

import com.testament.veltahleon.model.entities.religion.mythology.Demon;

import java.util.Collection;

public interface DemonRepository {

    Collection<Demon> getDemons();
    Demon getDemonByID(Long id);
    Boolean deleteDemonByID(Long id);
    Demon saveDemon(Demon demon);
    Demon updateDemon(Demon demon);
}
