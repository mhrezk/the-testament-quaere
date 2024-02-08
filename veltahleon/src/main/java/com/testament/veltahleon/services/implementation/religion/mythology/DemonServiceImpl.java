package com.testament.veltahleon.services.implementation.religion.mythology;

import com.testament.veltahleon.exceptions.DataNotFoundException;
import com.testament.veltahleon.model.entities.religion.mythology.Demon;
import com.testament.veltahleon.repositories.religion.mythology.DemonRepository;
import com.testament.veltahleon.services.ifc.religion.mythology.DemonService;
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
        return demonRepository.findByName(name);
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
