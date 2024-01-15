package com.testament.veltahleon.services.dao.implementation.religion.mythology;

import com.testament.veltahleon.model.entities.religion.mythology.Pantheon;
import com.testament.veltahleon.repositories.dao.ifc.religion.mythology.PantheonDAO;
import com.testament.veltahleon.services.dao.ifc.religion.mythology.PantheonService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class PantheonServiceImpl implements PantheonService {

    @Autowired
    private PantheonDAO pantheonDAO;

    @Override
    public Collection<Pantheon> getPantheons() {
        return pantheonDAO.getPantheons();
    }

    @Override
    public Pantheon getPantheonByID(Long id) {
        return pantheonDAO.getPantheonByID(id);
    }

    @Override
    @Transactional
    public Boolean deletePantheonByID(Long id) {
        return pantheonDAO.deletePantheonByID(id);
    }

    @Override
    @Transactional
    public Pantheon savePantheon(Pantheon pantheon) {
        return pantheonDAO.savePantheon(pantheon);
    }

    @Override
    @Transactional
    public Pantheon updatePantheon(Pantheon pantheon) {
        return pantheonDAO.updatePantheon(pantheon);
    }
}
