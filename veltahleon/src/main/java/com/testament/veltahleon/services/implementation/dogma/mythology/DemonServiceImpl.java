package com.testament.veltahleon.services.implementation.dogma.mythology;

import com.testament.veltahleon.exceptions.DataNotFoundException;
import com.testament.veltahleon.model.entities.dogma.mythology.Demon;
import com.testament.veltahleon.repositories.dogma.mythology.DemonRepository;
import com.testament.veltahleon.services.ifc.dogma.mythology.DemonService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DemonServiceImpl implements DemonService {

    @Autowired
    private DemonRepository demonRepository;

    @Override
    public Collection<Demon> getDemonsWithPagination(int pageNumber, int numberOfRecords) {
        return demonRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Demon> getDemons() {
        return demonRepository.findAll();
    }

    @Override
    public Demon getDemonByID(Long id) {
        return demonRepository.findById(id).orElseThrow(() -> new DataNotFoundException(String.format("Entry with id %d does not exist", id)));
    }

    @Override
    public Demon getDemonByName(String name) {
        if(demonRepository.countByName(name) <= 0) {
            Demon demon = new Demon();
            demon.setName(name.toUpperCase());
            demon.setImageURL(defaultImageURL("default.png"));
            return demonRepository.save(demon);
        } else {
            return demonRepository.findByName(name);
        }
    }

    @Override
    public Boolean deleteDemonByID(Long id) {
        demonRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Demon saveDemon(Demon demon) {
        return demonRepository.save(demon);
    }

    private String defaultImageURL(String imageName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/dogma/mythology/demons/images/" + imageName).toUriString();
    }

    @Override
    public Demon updateDemon(Long id, Demon demon) {
        Demon newDemon = demonRepository.findById(id).orElseThrow();

        if(demon.getName() != null && newDemon.getName() != demon.getName()) {
            newDemon.setName(demon.getName());
        }

        if(demon.getDescription() != null && newDemon.getDescription() != demon.getDescription()) {
            newDemon.setDescription(demon.getDescription());
        }

        if(demon.getImageURL() != null && newDemon.getImageURL() != demon.getImageURL()) {
            newDemon.setImageURL(demon.getImageURL());
        }

        return demonRepository.save(newDemon);
    }
}
