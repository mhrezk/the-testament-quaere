package com.testament.veltahleon.services.ifc.divination;

import com.testament.veltahleon.model.entities.divination.Constellation;

import java.util.Collection;

public interface ConstellationService {

    Collection<Constellation> getConstellationsWithPagination(int pageNumber, int numberOfRecords);
    Collection<Constellation> getConstellations();
    Constellation getConstellationByID(Long id);
    Boolean deleteConstellationByID(Long id);
    Constellation saveConstellation(Constellation constellation);
    Constellation updateConstellation(Long id, Constellation constellation);
}
