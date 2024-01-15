package com.testament.veltahleon.services.dao.implementation.religion;

import com.testament.veltahleon.model.entities.religion.Prophet;
import com.testament.veltahleon.repositories.dao.ifc.religion.ProphetDAO;
import com.testament.veltahleon.services.dao.ifc.religion.ProphetService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProphetServiceImpl implements ProphetService {

    @Autowired
    private ProphetDAO prophetDAO;

    @Override
    public Collection<Prophet> getProphets() {
        return prophetDAO.getProphets();
    }

    @Override
    public Prophet getProphetByID(Long id) {
        return prophetDAO.getProphetByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteProphetByID(Long id) {
        return prophetDAO.deleteProphetByID(id);
    }

    @Override
    @Transactional
    public Prophet saveProphet(Prophet prophet) {
        return prophetDAO.saveProphet(prophet);
    }

    @Override
    @Transactional
    public Prophet updateProphet(Prophet prophet) {
        return prophetDAO.updateProphet(prophet);
    }
}
