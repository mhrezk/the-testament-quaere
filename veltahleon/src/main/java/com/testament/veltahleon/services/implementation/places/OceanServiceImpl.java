package com.testament.veltahleon.services.implementation.places;

import com.testament.veltahleon.model.entities.places.Ocean;
import com.testament.veltahleon.repositories.places.OceanRepository;
import com.testament.veltahleon.services.ifc.places.OceanService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OceanServiceImpl implements OceanService {

    @Autowired
    private OceanRepository oceanRepository;

    @Override
    public Collection<Ocean> getOceans() {
        return oceanRepository.findAll();
    }

    @Override
    public Ocean getOceanByID(Long id) {
        return oceanRepository.findById(id).orElseThrow();
    }

    @Override
    public Ocean getOceanByName(String name) {
        return oceanRepository.findByName(name);
    }

    @Override
    public Boolean deleteOceanByID(Long id) {
        oceanRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Ocean saveOcean(Ocean ocean) {
        return oceanRepository.save(ocean);
    }

    @Override
    public Ocean updateOcean(Long id, Ocean ocean) {
        Ocean newOcean = oceanRepository.findById(id).orElseThrow();

        if(ocean.getName() != null && newOcean.getName() != ocean.getName()) {
            newOcean.setName(ocean.getName());
        }

        if(ocean.getDescription() != null && newOcean.getDescription() != ocean.getDescription()) {
            newOcean.setDescription(ocean.getDescription());
        }

        return oceanRepository.save(newOcean);
    }
}
