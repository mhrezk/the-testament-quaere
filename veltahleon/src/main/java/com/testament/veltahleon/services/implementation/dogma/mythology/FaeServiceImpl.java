package com.testament.veltahleon.services.implementation.dogma.mythology;

import com.testament.veltahleon.model.entities.dogma.mythology.Fae;
import com.testament.veltahleon.repositories.dogma.mythology.FaeRepository;
import com.testament.veltahleon.services.ifc.dogma.mythology.FaeService;
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
public class FaeServiceImpl implements FaeService {

    @Autowired
    private FaeRepository faeRepository;

    @Override
    public Collection<Fae> getFaesWithPagination(int pageNumber, int numberOfRecords) {
        return faeRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Fae> getFaes() {
        return faeRepository.findAll();
    }

    @Override
    public Collection<Fae> getFaesByRacialName(String name) {
        return faeRepository.findByRace_Name(name);
    }

    @Override
    public Fae getFaeByID(Long id) {
        return faeRepository.findById(id).orElseThrow();
    }

    @Override
    public Fae getFaeByName(String name) {
        return faeRepository.findByName(name);
    }

    @Override
    public Boolean deleteFaeByID(Long id) {
        faeRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Fae saveFae(Fae fae) {
        return faeRepository.save(fae);
    }

    @Override
    public Fae updateFae(Long id, Fae fae) {
        Fae newFae = faeRepository.findById(id).orElseThrow();

        if(fae.getName() != null && newFae.getName() != fae.getName()) {
            newFae.setName(fae.getName());
        }

        if(fae.getRace() != null && newFae.getRace() != fae.getRace()) {
            newFae.setRace(fae.getRace());
        }

        if(fae.getDescription() != null && newFae.getDescription() != fae.getDescription()) {
            newFae.setDescription(fae.getDescription());
        }

        if(fae.getImageURL() != null && newFae.getImageURL() != fae.getImageURL()) {
            newFae.setImageURL(fae.getImageURL());
        }

        return faeRepository.save(newFae);
    }
}
