package com.testament.veltahleon.services.entities.repo.ifc.places;

import com.testament.veltahleon.model.entities.places.Continent;

import java.util.Collection;

public interface ContinentService {

    Collection<Continent> getContinents();
    Continent getContinentByID(Long id);
    Boolean deleteContinentByID(Long id);
    Continent saveContinent(Continent continent);
    Continent updateContinent(Continent continent);
}
