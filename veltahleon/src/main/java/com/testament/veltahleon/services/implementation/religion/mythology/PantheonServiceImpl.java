package com.testament.veltahleon.services.implementation.religion.mythology;

import com.testament.veltahleon.exceptions.DataNotFoundException;
import com.testament.veltahleon.model.entities.religion.mythology.Pantheon;
import com.testament.veltahleon.repositories.religion.mythology.PantheonRepository;
import com.testament.veltahleon.services.ifc.religion.mythology.PantheonService;
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
        return pantheonRepository.findById(id).orElseThrow(() -> new DataNotFoundException(String.format("Entry with id %d does not exist", id)));
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
    public Pantheon updatePantheon(Long id, Pantheon pantheon) {
        Pantheon newPantheon = pantheonRepository.findById(id).orElseThrow();

        if(pantheon.getDeities() != null && newPantheon.getDeities() != pantheon.getDeities()) {
            newPantheon.setDeities(pantheon.getDeities());
        }

        return pantheonRepository.save(newPantheon);
    }
}
