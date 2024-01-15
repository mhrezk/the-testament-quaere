package com.testament.veltahleon.repositories.dao.ifc.places;

import com.testament.veltahleon.model.entities.places.Landmark;

import java.util.Collection;

public interface LandmarkDAO {

    Collection<Landmark> getLandmarks();
    Landmark getLandmarkByID(Long id);
    Boolean deleteLandmarkByID(Long id);
    Landmark saveLandmark(Landmark landmark);
    Landmark updateLandmark(Landmark landmark);
}
