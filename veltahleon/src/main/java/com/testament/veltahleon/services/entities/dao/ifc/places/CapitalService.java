package com.testament.veltahleon.services.entities.dao.ifc.places;

import com.testament.veltahleon.model.entities.places.Capital;

import java.util.Collection;

public interface CapitalService {

    Collection<Capital> getCapitals();
    Capital getCapitalByID(Long id);
    Boolean deleteCapitalByID(Long id);
    Capital saveCapital(Capital capital);
    Capital updateCapital(Capital capital);
}
