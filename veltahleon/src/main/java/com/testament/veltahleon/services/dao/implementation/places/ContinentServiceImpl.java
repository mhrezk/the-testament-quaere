package com.testament.veltahleon.services.dao.implementation.places;

import com.testament.veltahleon.model.entities.places.Continent;
import com.testament.veltahleon.repositories.dao.ifc.places.ContinentDAO;
import com.testament.veltahleon.services.dao.ifc.places.ContinentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ContinentServiceImpl implements ContinentService {

    @Autowired
    private ContinentDAO continentDAO;

    @Override
    public Collection<Continent> getContinents() {
        return continentDAO.getContinents();
    }

    @Override
    public Continent getContinentByID(Long id) {
        return continentDAO.getContinentByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteContinentByID(Long id) {
        return continentDAO.deleteContinentByID(id);
    }

    @Override
    @Transactional
    public Continent saveContinent(Continent continent) {
        return continentDAO.saveContinent(continent);
    }

    @Override
    @Transactional
    public Continent updateContinent(Continent continent) {
        return continentDAO.updateContinent(continent);
    }
}
