package com.testament.veltahleon.services.dao.implementation.places;

import com.testament.veltahleon.model.entities.places.Landmark;
import com.testament.veltahleon.repositories.dao.ifc.places.LandmarkDAO;
import com.testament.veltahleon.services.dao.ifc.places.LandmarkService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class LandmarkServiceImpl implements LandmarkService {

    @Autowired
    private LandmarkDAO landmarkDAO;

    @Override
    public Collection<Landmark> getLandmarks() {
        return landmarkDAO.getLandmarks();
    }

    @Override
    public Landmark getLandmarkByID(Long id) {
        return landmarkDAO.getLandmarkByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteLandmarkByID(Long id) {
        return landmarkDAO.deleteLandmarkByID(id);
    }

    @Override
    @Transactional
    public Landmark saveLandmark(Landmark landmark) {
        return landmarkDAO.saveLandmark(landmark);
    }

    @Override
    @Transactional
    public Landmark updateLandmark(Landmark landmark) {
        return landmarkDAO.updateLandmark(landmark);
    }
}
