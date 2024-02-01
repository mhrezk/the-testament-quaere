package com.testament.veltahleon.services.entities.repo.implementation.places;

import com.testament.veltahleon.model.entities.places.Nation;
import com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.places.NationRepository;
import com.testament.veltahleon.services.entities.repo.ifc.places.NationService;
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
public class NationServiceImpl implements NationService {

    @Autowired
    private NationRepository nationRepository;

    @Override
    public Collection<Nation> getNationsWithPagination(int pageNumber, int numberOfRecords) {
        return nationRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Nation> getNations() {
        return nationRepository.findAll();
    }

    @Override
    public Nation getNationByID(Long id) {
        return nationRepository.findById(id).orElseThrow();
    }

    @Override
    public Nation getNationByName(String name) {
        return nationRepository.findByName(name);
    }

    @Override
    public Boolean deleteNationByID(Long id) {
        nationRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Nation saveNation(Nation nation) {
        return nationRepository.save(nation);
    }

    @Override
    public Nation updateNation(Long id, Nation nation) {
        Nation newNation = nationRepository.findById(id).orElseThrow();

        if(nation.getName() != null && newNation.getName() != nation.getName()) {
            newNation.setName(nation.getName());
        }

        if(nation.getDescription() != null && newNation.getDescription() != nation.getDescription()) {
            newNation.setDescription(nation.getDescription());
        }

        if(nation.getCapital() != null && newNation.getCapital() != nation.getCapital()) {
            newNation.setCapital(nation.getCapital());
        }

        if(nation.getContinent() != null && newNation.getContinent() != nation.getContinent()) {
            newNation.setContinent(nation.getContinent());
        }

        if(nation.getLeader() != null && newNation.getLeader() != nation.getLeader()) {
            newNation.setLeader(nation.getLeader());
        }

        if(nation.getLanguage() != null && newNation.getLanguage() != nation.getLanguage()) {
            newNation.setLanguage(nation.getLanguage());
        }

        if(nation.getProvinces() != null && newNation.getProvinces() != nation.getProvinces()) {
            newNation.setProvinces(nation.getProvinces());
        }

        if(nation.getType() != null && newNation.getType() != nation.getType()) {
            newNation.setType(nation.getType());
        }

        if(nation.getPundits() != null && newNation.getPundits() != nation.getPundits()) {
            newNation.setPundits(nation.getPundits());
        }

        return nationRepository.save(newNation);
    }
}