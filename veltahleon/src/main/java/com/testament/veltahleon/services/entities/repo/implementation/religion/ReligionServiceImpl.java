package com.testament.veltahleon.services.entities.repo.implementation.religion;

import com.testament.veltahleon.exceptions.DataNotFoundException;
import com.testament.veltahleon.model.entities.religion.Religion;
import com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.religion.ReligionRepository;
import com.testament.veltahleon.services.entities.repo.ifc.religion.ReligionService;
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
public class ReligionServiceImpl implements ReligionService {

    @Autowired
    private ReligionRepository religionRepository;

    @Override
    public Collection<Religion> getReligionsWithPagination(int pageNumber, int numberOfRecords) {
        return religionRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Religion> getReligions() {
        return religionRepository.findAll();
    }

    @Override
    public Religion getReligionByID(Long id) {
        return religionRepository.findById(id).orElseThrow(() -> new DataNotFoundException(String.format("Entry with id %d does not exist", id)));
    }

    @Override
    public Religion getReligionByName(String name) {
        return religionRepository.findByName(name);
    }

    @Override
    public Boolean deleteReligionByID(Long id) {
        religionRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Religion saveReligion(Religion religion) {
        return religionRepository.save(religion);
    }

    @Override
    public Religion updateReligion(Long id, Religion religion) {
        Religion newReligion = religionRepository.findById(id).orElseThrow();

        if(religion.getName() != null && newReligion.getName() != religion.getName()) {
            newReligion.setName(religion.getName());
        }

        if(religion.getDescription() != null && newReligion.getDescription() != religion.getDescription()) {
            newReligion.setDescription(religion.getDescription());
        }

        if(religion.getPantheon() != null && newReligion.getPantheon() != religion.getPantheon()) {
            newReligion.setPantheon(religion.getPantheon());
        }

        return religionRepository.save(newReligion);
    }
}
