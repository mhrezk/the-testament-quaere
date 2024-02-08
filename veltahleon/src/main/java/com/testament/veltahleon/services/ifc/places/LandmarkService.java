package com.testament.veltahleon.services.ifc.places;

import com.testament.veltahleon.model.entities.places.Landmark;

import java.util.Collection;

public interface LandmarkService {

    Collection<Landmark> getLandmarksWithPagination(int pageNumber, int numberOfRecords);
    Collection<Landmark> getLandmarks();
    Collection<Landmark> getLandmarksByNationName(String name);
    Landmark getLandmarkByID(Long id);
    Landmark getLandmarkByName(String name);
    Boolean deleteLandmarkByID(Long id);
    Landmark saveLandmark(Landmark landmark);
    Landmark updateLandmark(Long id, Landmark landmark);
}
