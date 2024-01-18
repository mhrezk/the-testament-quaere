package com.testament.veltahleon.services.entities.repo.ifc.divination;

import com.testament.veltahleon.model.entities.divination.Constellation;

import java.util.Collection;

public interface ConstellationService {

    Collection<Constellation> getConstellations();
    Constellation getConstellationByID(Long id);
    Boolean deleteConstellationByID(Long id);
    Constellation saveConstellation(Constellation constellation);
    Constellation updateConstellation(Constellation constellation);
}
