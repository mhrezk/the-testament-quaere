package com.testament.veltahleon.services.implementation.places;

import com.testament.veltahleon.enumerations.GovernanceType;
import com.testament.veltahleon.enumerations.NationStatus;
import com.testament.veltahleon.enumerations.NationType;
import com.testament.veltahleon.model.entities.history.Language;
import com.testament.veltahleon.model.entities.places.Capital;
import com.testament.veltahleon.model.entities.places.Nation;
import com.testament.veltahleon.model.entities.places.NationDetails;
import com.testament.veltahleon.repositories.places.CapitalRepository;
import com.testament.veltahleon.repositories.places.NationDetailsRepository;
import com.testament.veltahleon.repositories.places.NationRepository;
import com.testament.veltahleon.services.ifc.places.NationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class NationServiceImpl implements NationService {

    @Autowired
    private NationRepository nationRepository;

    @Autowired
    private NationDetailsRepository nationDetailsRepository;

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
          newNation.setName(name.toUpperCase());
          newNation.setType(NationType.NONE);
          newNation.setNationStatus(NationStatus.INDEPENDENT);
          newNation.setGovernanceType(GovernanceType.NONE);
          newNation.setCapital(generateCapital());
          NationDetails nationDetails = generateNationDetails();
          nationDetails.setNation(newNation);
          nationDetailsRepository.save(nationDetails);
          return nationRepository.save(newNation);
        } else {
            return nationRepository.findByName(name);
        }
    }

    private Capital generateCapital() {
        Capital capital = new Capital();
        capital.setName("NONE");
        capital.setFlagURL(capitalImageURL("square.png"));
        return capital;
    }

    private String capitalImageURL(String imageName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/places/capitals/images/" + imageName).toUriString();
    }

    @Override
    public Boolean deleteNationByID(Long id) {
        nationRepository.deleteById(id);
        nationDetailsRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteNationByName(String name) {
        nationRepository.deleteByName(name);
        nationDetailsRepository.deleteByNation_Name(name);
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteNation(Nation nation) {
        nationRepository.delete(nation);
        nationDetailsRepository.deleteById(nation.getId());
        return Boolean.TRUE;
    }

    @Override
    public Nation saveNation(Nation nation) {
        nation.setName(nation.getName().toUpperCase());
        nationRepository.save(nation);
        NationDetails nationDetails = generateNationDetails();
        nationDetails.setNation(nation);
        nationDetailsRepository.save(nationDetails);
        return nation;
    }

    private NationDetails generateNationDetails() {
        ArrayList<Language> languages = new ArrayList<>();
        NationDetails nationDetails = new NationDetails();
        nationDetails.setFoundingYear(0);
        nationDetails.setEndingYear(0);
        nationDetails.setRulingPartyName("N/A");
        nationDetails.setRulingFamily("N/A");
        nationDetails.setSucceedingNation("None".toUpperCase());
        nationDetails.setPrecedingNation("None".toUpperCase());
        nationDetails.setProvincialNumber(0);
        nationDetails.setLeaderFirstName("None".toUpperCase());
        nationDetails.setLeaderSecondName("None".toUpperCase());
        nationDetails.setHistory(null);
        nationDetails.setLanguages(languages);
        nationDetails.setFlagURL(defaultImageURL("square.png"));
        return nationDetails;
    }

    private String defaultImageURL(String imageName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/places/nationDetails/images/" + imageName).toUriString();
    }

    @Override
    public Nation updateNation(Long id, Nation nation) {
        Nation newNation = nationRepository.findById(id).orElseThrow();

        if(nation.getName() != null && newNation.getName() != nation.getName()) {
            newNation.setName(nation.getName().toUpperCase());
        }

        if(nation.getType() != null && newNation.getType() != nation.getType()) {
            newNation.setType(nation.getType());
        }

        if(nation.getNationStatus() != null && newNation.getNationStatus() != nation.getNationStatus()) {
            newNation.setNationStatus(nation.getNationStatus());
        }

        if(nation.getGovernanceType() != null && newNation.getGovernanceType() != nation.getGovernanceType()) {
            newNation.setGovernanceType(nation.getGovernanceType());
        }

        NationDetails nationDetails = nationDetailsRepository.findById(id).orElseThrow();
        nationDetails.setNation(newNation);
        nationDetailsRepository.save(nationDetails);
        return nationRepository.save(newNation);
    }

    @Override
    public Nation modifyNation(Long id, Nation nation) {
        Nation newNation = nationRepository.findById(id).orElseThrow();
        newNation.setCapital(nation.getCapital());
        newNation.setName(nation.getName().toUpperCase());
        newNation.setGovernanceType(nation.getGovernanceType());
        newNation.setType(nation.getType());
        newNation.setNationStatus(nation.getNationStatus());
        NationDetails nationDetails = nationDetailsRepository.findById(id).orElseThrow();
        nationDetails.setNation(newNation);
        nationDetailsRepository.save(nationDetails);
        return nationRepository.save(newNation);
    }

    @Override
    public long countNations() {
        return nationRepository.count();
    }
}
