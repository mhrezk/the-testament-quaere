package com.testament.veltahleon.services.implementation.dogma;

import com.testament.veltahleon.exceptions.DataNotFoundException;
import com.testament.veltahleon.model.entities.dogma.Prophet;
import com.testament.veltahleon.model.entities.dogma.Religion;
import com.testament.veltahleon.model.entities.history.Letter;
import com.testament.veltahleon.repositories.dogma.ProphetRepository;
import com.testament.veltahleon.repositories.dogma.ReligionRepository;
import com.testament.veltahleon.services.ifc.dogma.ProphetService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProphetServiceImpl implements ProphetService {

    @Autowired
    private ProphetRepository prophetRepository;

    @Autowired
    private ReligionRepository religionRepository;

    @Override
    public Collection<Prophet> getProphetsWithPagination(int pageNumber, int numberOfRecords) {
        return prophetRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Prophet> getProphetsSortedWithPagination(int pageNumber, int numberOfRecords) {
        return prophetRepository.findAll(PageRequest.of(pageNumber, numberOfRecords, Sort.by(Sort.Direction.ASC, "name"))).toList();
    }

    @Override
    public Collection<Prophet> getProphetsWithPaginationByReligionName(String name, int pageNumber, int numberOfRecords) {
        return prophetRepository.findByReligion_Name(name, PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Prophet> getProphetsByReligionName(String name) {
        return prophetRepository.findByReligion_Name(name);
    }

    @Override
    public Collection<Prophet> getProphets() {
        return prophetRepository.findAll();
    }

    @Override
    public Prophet getProphetByID(Long id) {
        return prophetRepository.findById(id).orElseThrow(() -> new DataNotFoundException(String.format("Entry with id %d does not exist", id)));
    }

    @Override
    public Prophet getProphetByName(String name) {
        return prophetRepository.findByName(name);
    }

    @Override
    public Boolean deleteProphetByID(Long id) {
        prophetRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteProphetByName(String name) {
        Prophet prophet = prophetRepository.findByName(name);
        prophetRepository.delete(prophet);
        //return prophetRepository.deleteByName(name);
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteProphets() {
        prophetRepository.deleteAll();
        return Boolean.TRUE;
    }

    @Override
    public Prophet saveProphet(Prophet prophet, String name) {
        Religion religion = religionRepository.findByName(name);
        prophet.setReligion(religion);
        prophet.setName(prophet.getName().toUpperCase());
        prophet.setImageURL(defaultImageURL("default.png"));
        return prophetRepository.save(prophet);
    }

    @Override
    public Collection<Prophet> saveProphets(Collection<Prophet> prophets) {
        return prophetRepository.saveAll(prophets);
    }

    private String defaultImageURL(String imageName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/dogma/prophets/images/" + imageName).toUriString();
    }

    @Override
    public Prophet updateProphet(Long id, Prophet prophet) {
        Prophet newProphet = prophetRepository.findById(id).orElseThrow();

        if(prophet.getName() != null && newProphet.getName() != prophet.getName()) {
            newProphet.setName(prophet.getName().toUpperCase());
        }

        if(prophet.getImageURL() != null && newProphet.getImageURL() != prophet.getImageURL()) {
            newProphet.setImageURL(prophet.getImageURL());
        }

        if(prophet.getDescription() != null && newProphet.getDescription() != prophet.getDescription()) {
            newProphet.setDescription(prophet.getDescription());
        }
        return prophetRepository.save(newProphet);
    }

    @Override
    public Prophet modifyProphet(Long id, Prophet prophet) {
        Prophet newProphet = prophetRepository.findById(id).orElseThrow();
        newProphet.setName(prophet.getName().toUpperCase());
        newProphet.setDescription(prophet.getDescription());
        newProphet.setImageURL(prophet.getImageURL());
        newProphet.setReligion(prophet.getReligion());
        return prophetRepository.save(newProphet);
    }

    @Override
    public long countProphetsByReligionName(String religionName) {
        return prophetRepository.countByReligion_Name(religionName);
    }
}
