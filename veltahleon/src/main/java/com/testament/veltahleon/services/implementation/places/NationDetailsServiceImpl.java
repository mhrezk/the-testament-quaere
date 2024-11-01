package com.testament.veltahleon.services.implementation.places;

import com.testament.veltahleon.model.entities.history.Language;
import com.testament.veltahleon.model.entities.places.Nation;
import com.testament.veltahleon.model.entities.places.NationDetails;
import com.testament.veltahleon.repositories.places.NationDetailsRepository;
import com.testament.veltahleon.repositories.places.NationRepository;
import com.testament.veltahleon.services.ifc.places.NationDetailsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class NationDetailsServiceImpl implements NationDetailsService {

    @Autowired
    private NationDetailsRepository nationDetailsRepository;

    @Autowired
    private NationRepository nationRepository;

    @Override
    public NationDetails getNationDetailsByID(Long id) {
        return nationDetailsRepository.findById(id).orElseThrow();
    }

    @Override
    public NationDetails getNationDetailsByNationName(String name) {
        return nationDetailsRepository.findByNation_Name(name);
    }

    @Override
    public NationDetails updateNationDetails(Long id, NationDetails nationDetails) {
        NationDetails newNationDetails = nationDetailsRepository.findById(id).orElseThrow();
        Nation newNation = nationRepository.findById(id).orElseThrow();
        List<Language> languages = nationDetails.getLanguages();

        if(nationDetails.getNation().getName() != null && newNation.getName() != nationDetails.getNation().getName()) {
            newNation.setName(nationDetails.getNation().getName().toUpperCase());
        }

        if(nationDetails.getNation() != null && newNationDetails.getNation() != nationDetails.getNation()) {
            newNationDetails.setNation(nationDetails.getNation());
        }

        newNationDetails.setNation(newNation);

        if(nationDetails.getCapital().getName() != null && newNationDetails.getCapital().getName() != nationDetails.getCapital().getName()) {
            newNationDetails.setCapital(nationDetails.getCapital());
        }

        if(nationDetails.getLeaderFirstName() != null && newNationDetails.getLeaderFirstName() != nationDetails.getLeaderFirstName()) {
            newNationDetails.setLeaderFirstName(nationDetails.getLeaderFirstName().toUpperCase());
        }

        if(nationDetails.getLeaderSecondName() != null && newNationDetails.getLeaderSecondName() != nationDetails.getLeaderSecondName()) {
            newNationDetails.setLeaderSecondName(nationDetails.getLeaderSecondName().toUpperCase());
        }

        if(nationDetails.getProvincialNumber() != null && newNationDetails.getProvincialNumber() != nationDetails.getProvincialNumber()) {
            newNationDetails.setProvincialNumber(nationDetails.getProvincialNumber());
        }

        if(nationDetails.getRulingPartyName() != null && newNationDetails.getRulingPartyName() != nationDetails.getRulingPartyName()) {
            newNationDetails.setRulingPartyName(nationDetails.getRulingPartyName().toUpperCase());
        }

        if(nationDetails.getDescription() != null && newNationDetails.getDescription() != nationDetails.getDescription()) {
            newNationDetails.setDescription(nationDetails.getDescription());
        }

        if(nationDetails.getFlagURL() != null && newNationDetails.getFlagURL() != nationDetails.getFlagURL()) {
            newNationDetails.setFlagURL(nationDetails.getFlagURL());
        }
        return nationDetailsRepository.save(newNationDetails);
    }

    @Override
    public NationDetails modifyNationDetails(Long id, NationDetails nationDetails) {
        NationDetails newNationDetails = nationDetailsRepository.findById(id).orElseThrow();
        Nation newNation = nationRepository.findById(id).orElseThrow();
        newNation.setName(nationDetails.getNation().getName().toUpperCase());
        newNation.setType(nationDetails.getNation().getType());
        newNation.setGovernanceType(nationDetails.getNation().getGovernanceType());
        newNationDetails.setNation(newNation);
        newNationDetails.setDescription(nationDetails.getDescription());
        newNationDetails.setFlagURL(nationDetails.getFlagURL());
        newNationDetails.setLeaderFirstName(nationDetails.getLeaderFirstName());
        newNationDetails.setLeaderSecondName(nationDetails.getLeaderSecondName());
        newNationDetails.setProvincialNumber(nationDetails.getProvincialNumber());
        newNationDetails.setRulingPartyName(nationDetails.getRulingPartyName());
        return null;
    }

    @Override
    public Boolean deleteNationByID(Long id) {
        nationDetailsRepository.deleteById(id);
        return Boolean.TRUE;
    }
}
