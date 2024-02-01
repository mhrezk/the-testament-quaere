package com.testament.veltahleon.services.entities.repo.implementation.places;

import com.testament.veltahleon.model.entities.places.NationType;
import com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.places.NationTypeRepository;
import com.testament.veltahleon.services.entities.repo.ifc.places.NationTypeService;
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
public class NationTypeServiceImpl implements NationTypeService {

    @Autowired
    private NationTypeRepository nationTypeRepository;

    @Override
    public Collection<NationType> getNationTypes() {
        return nationTypeRepository.findAll();
    }

    @Override
    public NationType getNationTypeByID(Long id) {
        return nationTypeRepository.findById(id).orElseThrow();
    }

    @Override
    public NationType getNationTypeByType(String type) {
        return nationTypeRepository.findByName(type);
    }

    @Override
    public Boolean deleteNationTypeByID(Long id) {
        nationTypeRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public NationType saveNationType(NationType nationType) {
        return nationTypeRepository.save(nationType);
    }

    @Override
    public NationType updateNationType(Long id, NationType nationType) {
        NationType newNationType = nationTypeRepository.findById(id).orElseThrow();

        if(nationType.getName() != null && newNationType.getName() != nationType.getName()) {
            newNationType.setName(nationType.getName());
        }

        if(nationType.getDescription() != null && newNationType.getDescription() != nationType.getDescription()) {
            newNationType.setDescription(nationType.getDescription());
        }

        return nationTypeRepository.save(newNationType);
    }
}
