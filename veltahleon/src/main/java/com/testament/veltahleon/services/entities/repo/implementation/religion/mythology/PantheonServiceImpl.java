package com.testament.veltahleon.services.entities.repo.implementation.religion.mythology;

import com.testament.veltahleon.model.entities.religion.mythology.Pantheon;
import com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.religion.mythology.PantheonRepository;
import com.testament.veltahleon.services.entities.repo.ifc.religion.mythology.PantheonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class PantheonServiceImpl implements PantheonService {

    @Autowired
    private PantheonRepository pantheonRepository;

    @Override
    public Collection<Pantheon> getPantheonsWithPagination(int pageNumber, int numberOfRecords) {
        return pantheonRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Pantheon> getPantheons() {
        return pantheonRepository.findAll();
    }

    @Override
    public Pantheon getPantheonByID(Long id) {
        return pantheonRepository.findById(id).orElseThrow();
    }

    @Override
    public Boolean deletePantheonByID(Long id) {
        pantheonRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Pantheon savePantheon(Pantheon pantheon) {
        return pantheonRepository.save(pantheon);
    }

    @Override
    public Pantheon updatePantheon(Pantheon pantheon) {
        return pantheonRepository.save(pantheon);
    }
}