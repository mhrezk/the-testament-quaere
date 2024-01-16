package com.testament.veltahleon.repositories.repo.ifc.places;

import com.testament.veltahleon.model.entities.places.Continent;

import java.util.Collection;

public interface ContinentRepository {

    Collection<Continent> getContinents();
    Continent getContinentByID(Long id);
    Boolean deleteContinentByID(Long id);
    Continent saveContinent(Continent continent);
    Continent updateContinent(Continent continent);
}
