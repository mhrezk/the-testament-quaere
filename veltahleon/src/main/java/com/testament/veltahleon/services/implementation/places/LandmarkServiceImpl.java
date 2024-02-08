package com.testament.veltahleon.services.implementation.places;

import com.testament.veltahleon.model.entities.places.Landmark;
import com.testament.veltahleon.repositories.places.LandmarkRepository;
import com.testament.veltahleon.services.ifc.places.LandmarkService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class LandmarkServiceImpl implements LandmarkService {

    @Autowired
    private LandmarkRepository landmarkRepository;

    @Override
    public Collection<Landmark> getLandmarksWithPagination(int pageNumber, int numberOfRecords) {
        return landmarkRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Landmark> getLandmarks() {
        return landmarkRepository.findAll();
    }

    @Override
    public Collection<Landmark> getLandmarksByNationName(String name) {
        return landmarkRepository.findByNation_Name(name);
    }

    @Override
    public Landmark getLandmarkByID(Long id) {
        return landmarkRepository.findById(id).orElseThrow();
    }

    @Override
    public Landmark getLandmarkByName(String name) {
        return landmarkRepository.findByName(name);
    }

    @Override
    public Boolean deleteLandmarkByID(Long id) {
        landmarkRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Landmark saveLandmark(Landmark landmark) {
        return landmarkRepository.save(landmark);
    }

    @Override
    public Landmark updateLandmark(Long id, Landmark landmark) {
        Landmark newLandmark = landmarkRepository.findById(id).orElseThrow();

        if(landmark.getName() != null && newLandmark.getName() != landmark.getName()) {
            newLandmark.setName(landmark.getName());
        }

        if(landmark.getNation() != null && newLandmark.getNation() != landmark.getNation()) {
            newLandmark.setNation(landmark.getNation());
        }

        return landmarkRepository.save(newLandmark);
    }
}
