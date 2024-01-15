package com.testament.veltahleon.services.entities.dao.implementation.places;

import com.testament.veltahleon.model.entities.places.Nation;
import com.testament.veltahleon.repositories.dao.ifc.places.NationDAO;
import com.testament.veltahleon.services.entities.dao.ifc.places.NationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class NationServiceImpl implements NationService {

    @Autowired
    private NationDAO nationDAO;

    @Override
    public Collection<Nation> getNations() {
        return nationDAO.getNations();
    }

    @Override
    public Nation getNationByID(Long id) {
        return nationDAO.getNationByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteNationByID(Long id) {
        return nationDAO.deleteNationByID(id);
    }

    @Override
    @Transactional
    public Nation saveNation(Nation nation) {
        return nationDAO.saveNation(nation);
    }

    @Override
    @Transactional
    public Nation updateNation(Nation nation) {
        return nationDAO.updateNation(nation);
    }
}
