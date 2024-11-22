package com.testament.veltahleon.services.implementation.dogma.mythology;

import com.testament.veltahleon.exceptions.DataNotFoundException;
import com.testament.veltahleon.model.entities.dogma.Religion;
import com.testament.veltahleon.model.entities.dogma.mythology.Deity;
import com.testament.veltahleon.repositories.dogma.ReligionRepository;
import com.testament.veltahleon.repositories.dogma.mythology.DeityRepository;
import com.testament.veltahleon.services.ifc.dogma.mythology.DeityService;
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
public class DeityServiceImpl implements DeityService {

    @Autowired
    private final DeityRepository deityRepository;

    @Autowired
    private final ReligionRepository religionRepository;

    @Override
    public Collection<Deity> getDeitiesWithPagination(int pageNumber, int numberOfRecords) {
        return deityRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Deity> getDeitiesWithPaginationByReligionName(String name, int pageNumber, int numberOfRecords) {
        return deityRepository.findByReligion_Name(name, PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Deity> getDeitiesByReligionName(String name) {
        return deityRepository.findByReligion_Name(name);
    }

    @Override
    public Collection<Deity> getDeities() {
        return deityRepository.findAll();
    }

    @Override
    public Deity getDeityByID(Long id) {
        return deityRepository.findById(id).orElseThrow(() -> new DataNotFoundException(String.format("Entry with id %d does not exist", id)));
    }

    @Override
    public Deity getDeityByName(String name) {
        if(deityRepository.countByName(name) <= 0) {
            Deity deity = new Deity();
            deity.setName(name.toUpperCase());
            deity.setImageURL(defaultImageURL("mosque.png"));
        }
        return deityRepository.findByName(name);
    }

    @Override
    public Boolean deleteDeityByID(Long id) {
        deityRepository.findById(id);
        return Boolean.TRUE;
    }

    @Override
    public Deity saveDeity(Deity deity, String religionName) {
        Religion religion = religionRepository.findByName(religionName);
        deity.setName(deity.getName().toUpperCase());
        deity.setImageURL(defaultImageURL("mosque.png"));
        deity.setReligion(religion);
        return deityRepository.save(deity);
    }

    private String defaultImageURL(String imageName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/dogma/mythology/deities/images/" + imageName).toUriString();
    }

    @Override
    public Deity updateDeity(Long id, Deity deity) {
        Deity newDeity = deityRepository.findById(id).orElseThrow();

        if(deity.getName() != null && newDeity.getName() != deity.getName()) {
            newDeity.setName(deity.getName().toUpperCase());
        }

        if(deity.getPowerDomain() != null && newDeity.getPowerDomain() != deity.getPowerDomain()) {
            newDeity.setPowerDomain(deity.getPowerDomain());
        }

        if(deity.getDescription() != null && newDeity.getDescription() != deity.getDescription()) {
            newDeity.setDescription(deity.getDescription());
        }

        if(deity.getImageURL() != null && newDeity.getImageURL() != deity.getImageURL()) {
            newDeity.setImageURL(deity.getImageURL());
        }

        return deityRepository.save(newDeity);
    }

    @Override
    public Deity modifyDeity(Long id, Deity deity) {
        Deity newDeity = deityRepository.findById(id).orElseThrow();
        newDeity.setName(deity.getName().toUpperCase());
        newDeity.setDescription(deity.getDescription());
        newDeity.setImageURL(deity.getImageURL());
        newDeity.setPowerDomain(deity.getPowerDomain());
        return deityRepository.save(newDeity);
    }

    @Override
    public long countDeitiesByReligionName(String name) {
        return deityRepository.countByReligion_Name(name);
    }
}
