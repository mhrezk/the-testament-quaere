package com.testament.veltahleon.services.implementation.dogma;

import com.testament.veltahleon.exceptions.DataNotFoundException;
import com.testament.veltahleon.model.entities.dogma.Prophet;
import com.testament.veltahleon.repositories.dogma.ProphetRepository;
import com.testament.veltahleon.services.ifc.dogma.ProphetService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProphetServiceImpl implements ProphetService {

    @Autowired
    private ProphetRepository prophetRepository;

    @Override
    public Collection<Prophet> getProphetsWithPagination(int pageNumber, int numberOfRecords) {
        return prophetRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Prophet> getProphetsSortedWithPagination(int pageNumber, int numberOfRecords) {
        return prophetRepository.findAll(PageRequest.of(pageNumber, numberOfRecords, Sort.by(Sort.Direction.ASC, "name"))).toList();
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
    public Prophet saveProphet(Prophet prophet) {
        return prophetRepository.save(prophet);
    }

    @Override
    public Collection<Prophet> saveProphets(Collection<Prophet> prophets) {
        return prophetRepository.saveAll(prophets);
    }

    @Override
    public Prophet updateProphet(Long id, Prophet prophet) {
        Prophet newProphet = prophetRepository.findById(id).orElseThrow();

        if(prophet.getName() != null && newProphet.getName() != prophet.getName()) {
            newProphet.setName(prophet.getName());
        }

        if(prophet.getDescription() != null && newProphet.getDescription() != prophet.getDescription()) {
            newProphet.setDescription(prophet.getDescription());
        }
        return prophetRepository.save(newProphet);
    }
}
