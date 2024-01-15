package com.testament.veltahleon.services.entities.dao.ifc.religion.mythology;

import com.testament.veltahleon.model.entities.religion.mythology.Demon;

import java.util.Collection;

public interface DemonService {

    Collection<Demon> getDemons();
    Demon getDemonByID(Long id);
    Boolean deleteDemonByID(Long id);
    Demon saveDemon(Demon demon);
    Demon updateDemon(Demon demon);
}
