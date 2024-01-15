package com.testament.veltahleon.services.dao.ifc.religion;

import com.testament.veltahleon.model.entities.religion.Prophet;

import java.util.Collection;

public interface ProphetService {

    Collection<Prophet> getProphets();
    Prophet getProphetByID(Long id);
    Boolean deleteProphetByID(Long id);
    Prophet saveProphet(Prophet prophet);
    Prophet updateProphet(Prophet prophet);
}
