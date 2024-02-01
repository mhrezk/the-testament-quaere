package com.testament.veltahleon.services.entities.repo.implementation.places;

import com.testament.veltahleon.model.entities.places.Continent;
import com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.places.ContinentRepository;
import com.testament.veltahleon.services.entities.repo.ifc.places.ContinentService;
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
public class ContinentServiceImpl implements ContinentService {

    @Autowired
    private ContinentRepository continentRepository;

    @Override
    public Collection<Continent> getContinentsWithPagination(int pageNumber, int numberOfRecords) {
        return continentRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Continent> getContinents() {
        return continentRepository.findAll();
    }

    @Override
    public Continent getContinentByID(Long id) {
        return continentRepository.findById(id).orElseThrow();
    }

    @Override
    public Continent getContinentByName(String name) {
        return continentRepository.findByName(name);
    }

    @Override
    public Boolean deleteContinentByID(Long id) {
        continentRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Continent saveContinent(Continent continent) {
        return continentRepository.save(continent);
    }

    @Override
    public Continent updateContinent(Long id, Continent continent) {
        Continent newContinent = continentRepository.findById(id).orElseThrow();

        if(continent.getName() != null && newContinent.getName() != continent.getName()) {
            newContinent.setName(continent.getName());
        }

        if(continent.getDescription() != null && newContinent.getDescription() != continent.getDescription()) {
            newContinent.setDescription(continent.getDescription());
        }

        return continentRepository.save(newContinent);
    }
}
