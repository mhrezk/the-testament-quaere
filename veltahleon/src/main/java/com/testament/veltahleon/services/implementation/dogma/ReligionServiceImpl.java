package com.testament.veltahleon.services.implementation.dogma;

import com.testament.veltahleon.exceptions.DataNotFoundException;
import com.testament.veltahleon.model.entities.dogma.Religion;
import com.testament.veltahleon.repositories.dogma.ReligionRepository;
import com.testament.veltahleon.services.ifc.dogma.ReligionService;
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
        if(religionRepository.countByName(name) <= 0) {
            Religion newReligion = new Religion();
            String firstLetter = name.substring(0, 1).toUpperCase();
            String word = name.substring(1).toLowerCase();
            newReligion.setName(firstLetter + word);
            return religionRepository.save(newReligion);
        } else {
            return religionRepository.findByName(name);
        }
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

        if(religion.getDeities() != null && newReligion.getDeities() != religion.getDeities()) {
            newReligion.setDeities(religion.getDeities());
        }

        return religionRepository.save(newReligion);
    }
}
