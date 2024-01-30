package com.testament.veltahleon.services.entities.repo.implementation.religion.mythology;

import com.testament.veltahleon.model.entities.religion.mythology.Angel;
import com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.religion.mythology.AngelRepository;
import com.testament.veltahleon.services.entities.repo.ifc.religion.mythology.AngelService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
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
        return angelRepository.findById(id).orElseThrow();
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
    public Angel updateAngel(Angel angel) {
        return angelRepository.save(angel);
    }
}
