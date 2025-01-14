package com.testament.veltahleon.services.implementation.dogma.mythology;

import com.testament.veltahleon.exceptions.DataNotFoundException;
import com.testament.veltahleon.model.entities.dogma.Religion;
import com.testament.veltahleon.model.entities.dogma.mythology.Angel;
import com.testament.veltahleon.repositories.dogma.ReligionRepository;
import com.testament.veltahleon.repositories.dogma.mythology.AngelRepository;
import com.testament.veltahleon.services.ifc.dogma.mythology.AngelService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AngelServiceImpl implements AngelService {

//    @Autowired
//    private final EntityManager entityManager;

    @Autowired
    private final AngelRepository angelRepository;

    @Autowired
    private final ReligionRepository religionRepository;

    @Override
    public Collection<Angel> getAngelsWithPagination(int pageNumber, int numberOfRecords) {
        return angelRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Angel> getAngelsByReligionNameWithPagination(int pageNumber, int numberOfRecords, String religionName) {
        return angelRepository.findByReligion_Name(religionName, PageRequest.of(pageNumber, numberOfRecords)).toList();
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
        angel.setReligion(religionRepository.findByName(angel.getReligion().getName()));
        angel.setName(angel.getName().toUpperCase());
        angel.setImageURL(defaultImageURL("default.png"));
        return angelRepository.save(angel);
    }

    private String defaultImageURL(String imageName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/dogma/mythology/angels/images/" + imageName).toUriString();
    }

    @Override
    public Angel updateAngel(Long id, Angel angel) {
        Angel newAngel = angelRepository.findById(id).orElseThrow();

        if(angel.getName() != null && newAngel.getName() != angel.getName()) {
            newAngel.setName(angel.getName());
        }

        if(angel.getRace() != null && newAngel.getRace() != angel.getRace()) {
            newAngel.setRace(angel.getRace());
        }

        if(angel.getReligion() != null && newAngel.getReligion() != angel.getReligion()) {
            newAngel.setReligion(angel.getReligion());
        }

        if(angel.getPowerDomain() != null && newAngel.getPowerDomain() != angel.getPowerDomain()) {
            newAngel.setPowerDomain(angel.getPowerDomain());
        }

        if(angel.getDescription() != null && newAngel.getDescription() != angel.getDescription()) {
            newAngel.setDescription(angel.getDescription());
        }

        if(angel.getImageURL() != null && newAngel.getImageURL() != angel.getImageURL()) {
            newAngel.setImageURL(angel.getImageURL());
        }

        return angelRepository.save(newAngel);
    }

    @Override
    public Angel modifyAngel(Long id, Angel angel) {
        return Angel.builder()
                .name(angel.getName())
                .powerDomain(angel.getPowerDomain())
                .description(angel.getDescription())
                .imageURL(angel.getImageURL())
                .race(angel.getRace())
                .religion(angel.getReligion())
                .build();
    }

    @Override
    public long countAngelsByReligionName(String religionName) {
        return angelRepository.countByReligion_Name(religionName);
    }

    @Override
    public long countAngels() {
        return angelRepository.count();
    }
}