package com.testament.veltahleon.services.ifc.places;

import com.testament.veltahleon.model.entities.places.Capital;

import java.util.Collection;

public interface CapitalService {

    Collection<Capital> getCapitalsWithPagination(int pageNumber, int numberOfRecords);
    Collection<Capital> getCapitals();
    Capital getCapitalByID(Long id);
    Capital getCapitalByName(String name);
    //Capital getCapitalByNationName(String name);
    Boolean deleteCapitalByID(Long id);
    Capital saveCapital(Capital capital);
    Capital updateCapital(Long id, Capital capital);
}
