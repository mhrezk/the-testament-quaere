package com.testament.veltahleon.services.entities.dao.ifc.religion.mythology;

import com.testament.veltahleon.model.entities.religion.mythology.Angel;

import java.util.Collection;

public interface AngelService {

    Collection<Angel> getAngels();
    Angel getAngelByID(Long id);
    Boolean deleteAngelByID(Long id);
    Angel saveAngel(Angel angel);
    Angel updateAngel(Angel angel);
}
