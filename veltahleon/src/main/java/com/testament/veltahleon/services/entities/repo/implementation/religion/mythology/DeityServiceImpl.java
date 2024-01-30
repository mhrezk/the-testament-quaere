package com.testament.veltahleon.services.entities.repo.implementation.religion.mythology;

import com.testament.veltahleon.model.entities.religion.mythology.Deity;
import com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.religion.mythology.DeityRepository;
import com.testament.veltahleon.services.entities.repo.ifc.religion.mythology.DeityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeityServiceImpl implements DeityService {

    @Autowired
    private final DeityRepository deityRepository;
    @Override
    public Collection<Deity> getDeitiesWithPagination(int pageNumber, int numberOfRecords) {
        return deityRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Deity> getDeities() {
        return deityRepository.findAll();
    }

    @Override
    public Deity getDeityByID(Long id) {
        return deityRepository.findById(id).orElseThrow();
    }

    @Override
    public Deity getDeityByName(String name) {
        return deityRepository.findByName(name);
    }

    @Override
    public Boolean deleteDeityByID(Long id) {
        deityRepository.findById(id);
        return Boolean.TRUE;
    }

    @Override
    public Deity saveDeity(Deity deity) {
        return deityRepository.save(deity);
    }

    @Override
    public Deity updateDeity(Deity deity) {
        return deityRepository.save(deity);
    }
}