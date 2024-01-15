package com.testament.veltahleon.services.entities.dao.implementation.places;

import com.testament.veltahleon.model.entities.places.Capital;
import com.testament.veltahleon.repositories.dao.ifc.places.CapitalDAO;
import com.testament.veltahleon.services.entities.dao.ifc.places.CapitalService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class CapitalServiceImpl implements CapitalService {

    @Autowired
    private CapitalDAO capitalDAO;

    @Override
    public Collection<Capital> getCapitals() {
        return capitalDAO.getCapitals();
    }

    @Override
    public Capital getCapitalByID(Long id) {
        return capitalDAO.getCapitalByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteCapitalByID(Long id) {
        return capitalDAO.deleteCapitalByID(id);
    }

    @Override
    @Transactional
    public Capital saveCapital(Capital capital) {
        return capitalDAO.saveCapital(capital);
    }

    @Override
    @Transactional
    public Capital updateCapital(Capital capital) {
        return capitalDAO.updateCapital(capital);
    }
}
