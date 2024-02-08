package com.testament.veltahleon.services.ifc.places;

import com.testament.veltahleon.model.entities.places.Continent;

import java.util.Collection;

public interface ContinentService {

    Collection<Continent> getContinentsWithPagination(int pageNumber, int numberOfRecords);
    Collection<Continent> getContinents();
    Continent getContinentByID(Long id);
    Continent getContinentByName(String name);
    Boolean deleteContinentByID(Long id);
    Continent saveContinent(Continent continent);
    Continent updateContinent(Long id, Continent continent);
}
