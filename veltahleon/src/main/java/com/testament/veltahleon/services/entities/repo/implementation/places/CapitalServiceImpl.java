package com.testament.veltahleon.services.entities.repo.implementation.places;

import com.testament.veltahleon.model.entities.places.Capital;
import com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.places.CapitalRepository;
import com.testament.veltahleon.services.entities.repo.ifc.places.CapitalService;
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
public class CapitalServiceImpl implements CapitalService {

    @Autowired
    private CapitalRepository capitalRepository;

    @Override
    public Collection<Capital> getCapitalsWithPagination(int pageNumber, int numberOfRecords) {
        return capitalRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Capital> getCapitals() {
        return capitalRepository.findAll();
    }

    @Override
    public Capital getCapitalByID(Long id) {
        return capitalRepository.findById(id).orElseThrow();
    }

    @Override
    public Capital getCapitalByName(String name) {
        return capitalRepository.findByName(name);
    }

    @Override
    public Capital getCapitalByNationName(String name) {
        return capitalRepository.findByNation_Name(name);
    }

    @Override
    public Boolean deleteCapitalByID(Long id) {
        capitalRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Capital saveCapital(Capital capital) {
        return capitalRepository.save(capital);
    }

    @Override
    public Capital updateCapital(Long id, Capital capital) {
        Capital newCapital = capitalRepository.findById(id).orElseThrow();

        if(capital.getName() != null && newCapital.getName() != capital.getName()) {
            newCapital.setName(capital.getName());
        }

        if(capital.getDescription() != null && newCapital.getDescription() != capital.getDescription()) {
            newCapital.setDescription(capital.getDescription());
        }

        if(capital.getNation() != null && newCapital.getNation() != capital.getNation()) {
            newCapital.setNation(capital.getNation());
        }

        return capitalRepository.save(newCapital);
    }
}
