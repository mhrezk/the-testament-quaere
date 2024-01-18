package com.testament.veltahleon.services.entities.repo.ifc.places;

import com.testament.veltahleon.model.entities.places.Landmark;

import java.util.Collection;

public interface LandmarkService {

    Collection<Landmark> getLandmarks();
    Landmark getLandmarkByID(Long id);
    Boolean deleteLandmarkByID(Long id);
    Landmark saveLandmark(Landmark landmark);
    Landmark updateLandmark(Landmark landmark);
}
