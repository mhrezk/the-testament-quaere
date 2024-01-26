package com.testament.veltahleon.services.entities.repo.ifc.places;

import com.testament.veltahleon.model.entities.places.Ocean;

import java.util.Collection;

public interface OceanService {

    Collection<Ocean> getOceans();
    Ocean getOceanByID(Long id);
    Boolean deleteOceanByID(Long id);
    Ocean saveOcean(Ocean ocean);
    Ocean updateOcean(Ocean ocean);
}