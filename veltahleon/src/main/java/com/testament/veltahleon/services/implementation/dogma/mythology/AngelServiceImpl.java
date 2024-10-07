package com.testament.veltahleon.services.implementation.dogma.mythology;

import com.testament.veltahleon.exceptions.DataNotFoundException;
import com.testament.veltahleon.model.entities.dogma.mythology.Angel;
import com.testament.veltahleon.repositories.dogma.mythology.AngelRepository;
import com.testament.veltahleon.services.ifc.dogma.mythology.AngelService;
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
public class AngelServiceImpl implements AngelService {

//    @Autowired
//    private final EntityManager entityManager;

    @Autowired
    private final AngelRepository angelRepository;

    @Override
    public Collection<Angel> getAngelsWithPagination(int pageNumber, int numberOfRecords) {
        return angelRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Angel> getAngels() {
        return angelRepository.findAll();
    }

    @Override
    public Angel getAngelByID(Long id) {
        return angelRepository.findById(id).orElseThrow(() -> new DataNotFoundException(String.format("Entry with id %d does not exist", id)));
    }

    @Override
    public Angel getAngelByName(String name) {
        return angelRepository.findByName(name);
    }

    @Override
    public Boolean deleteAngelByID(Long id) {
        angelRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Angel saveAngel(Angel angel) {
        return angelRepository.save(angel);
    }

    @Override
    public Angel updateAngel(Long id, Angel angel) {
        Angel newAngel = angelRepository.findById(id).orElseThrow();

        if(angel.getName() != null && newAngel.getName() != angel.getName()) {
            newAngel.setName(angel.getName());
        }

        if(angel.getDescription() != null && newAngel.getDescription() != angel.getDescription()) {
            newAngel.setDescription(angel.getDescription());
        }

        if(angel.getImageURL() != null && newAngel.getImageURL() != angel.getImageURL()) {
            newAngel.setImageURL(angel.getImageURL());
        }

        return angelRepository.save(newAngel);
    }
}
