package com.testament.veltahleon.services.implementation.divination;

import com.testament.veltahleon.model.entities.divination.Constellation;
import com.testament.veltahleon.repositories.divination.ConstellationRepository;
import com.testament.veltahleon.services.ifc.divination.ConstellationService;
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
public class ConstellationServiceImpl implements ConstellationService {

    @Autowired
    private ConstellationRepository constellationRepository;

    @Override
    public Collection<Constellation> getConstellationsWithPagination(int pageNumber, int numberOfRecords) {
        return constellationRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Constellation> getConstellations() {
        return constellationRepository.findAll();
    }

    @Override
    public Constellation getConstellationByID(Long id) {
        return constellationRepository.findById(id).orElseThrow();
    }

    @Override
    public Boolean deleteConstellationByID(Long id) {
        constellationRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Constellation saveConstellation(Constellation constellation) {
        return constellationRepository.save(constellation);
    }

    @Override
    public Constellation updateConstellation(Long id, Constellation constellation) {
        Constellation newConstellation = constellationRepository.findById(id).orElseThrow();

        if(constellation.getName() != null && newConstellation.getName() != constellation.getName()) {
            newConstellation.setName(constellation.getName());
        }

        if(constellation.getDescription() != null && newConstellation.getDescription() != constellation.getDescription()) {
            newConstellation.setDescription(constellation.getDescription());
        }

        if(constellation.getImageURL() != null && newConstellation.getImageURL() != constellation.getImageURL()) {
            newConstellation.setImageURL(constellation.getImageURL());
        }

        return constellationRepository.save(newConstellation);
    }
}
