package com.testament.veltahleon.repositories.dao.ifc.religion.mythology;

import com.testament.veltahleon.model.entities.religion.mythology.Angel;

import java.util.Collection;

public interface AngelDAO {

    Collection<Angel> getAngels();
    Angel getAngelByID(Long id);
    Boolean deleteAngelByID(Long id);
    Angel saveAngel(Angel angel);
    Angel updateAngel(Angel angel);
}
