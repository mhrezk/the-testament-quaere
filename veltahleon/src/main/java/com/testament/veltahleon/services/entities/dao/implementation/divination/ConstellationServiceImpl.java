package com.testament.veltahleon.services.entities.dao.implementation.divination;

import com.testament.veltahleon.model.entities.divination.Constellation;
import com.testament.veltahleon.repositories.dao.ifc.divination.ConstellationDAO;
import com.testament.veltahleon.services.entities.dao.ifc.divination.ConstellationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConstellationServiceImpl implements ConstellationService {

    @Autowired
    private ConstellationDAO constellationDAO;

    @Override
    public Collection<Constellation> getConstellations() {
        return constellationDAO.getConstellations();
    }

    @Override
    public Constellation getConstellationByID(Long id) {
        return constellationDAO.getConstellationByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteConstellationByID(Long id) {
        return constellationDAO.deleteConstellationByID(id);
    }

    @Override
    @Transactional
    public Constellation saveConstellation(Constellation constellation) {
        return constellationDAO.saveConstellation(constellation);
    }

    @Override
    @Transactional
    public Constellation updateConstellation(Constellation constellation) {
        return constellationDAO.updateConstellation(constellation);
    }
}
