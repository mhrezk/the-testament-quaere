package com.testament.veltahleon.services.implementation.places;

import com.testament.veltahleon.model.entities.places.Nation;
import com.testament.veltahleon.repositories.places.NationRepository;
import com.testament.veltahleon.services.ifc.places.NationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

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
    public Collection<Nation> getNationsByNationType(String nationType) {
        return nationRepository.findByType(nationType);
    }

    @Override
    public Collection<Nation> getNationsByGovernanceType(String governanceType) {
        return nationRepository.findByGovernanceType(governanceType);
    }

    @Override
    public Nation getNationByID(Long id) {
        return nationRepository.findById(id).orElseThrow();
    }

    @Override
    public Nation getNationByName(String name) {
        if(nationRepository.countByName(name) <= 0) {
          Nation newNation = new Nation();
          String firstLetter = name.substring(0, 1).toUpperCase();
          String word = name.substring(1).toLowerCase();
          newNation.setName(firstLetter + word);
          return nationRepository.save(newNation);
        } else {
            return nationRepository.findByName(name);
        }
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

//        if(nation.getContinent() != null && newNation.getContinent() != nation.getContinent()) {
//            newNation.setContinent(nation.getContinent());
//        }

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

        if(nation.getGovernanceType() != null && newNation.getGovernanceType() != nation.getGovernanceType()) {
            newNation.setGovernanceType(nation.getGovernanceType());
        }

        if(nation.getUrlFlag() != null && newNation.getUrlFlag() != nation.getUrlFlag()) {
            newNation.setUrlFlag(nation.getUrlFlag());
        }

        return nationRepository.save(newNation);
    }

    @Override
    public Nation modifyNation(Long id, Nation nation) {
        Nation newNation = nationRepository.findById(id).orElseThrow();
        newNation.setName(nation.getName());
        newNation.setDescription(nation.getDescription());
        newNation.setGovernanceType(nation.getGovernanceType());
        newNation.setType(nation.getType());
        newNation.setLeader(nation.getLeader());
        newNation.setCapital(nation.getCapital());
        newNation.setUrlFlag(nation.getUrlFlag());
        newNation.setLanguage(nation.getLanguage());
        newNation.setProvinces(nation.getProvinces());
        return nationRepository.save(newNation);
    }
}
