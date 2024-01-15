package com.testament.veltahleon.services.entities.dao.implementation.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Unit;
import com.testament.veltahleon.repositories.dao.ifc.politics.military.UnitDAO;
import com.testament.veltahleon.services.entities.dao.ifc.politics.military.UnitService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class UnitServiceImpl implements UnitService {

    @Autowired
    private UnitDAO unitDAO;

    @Override
    public Collection<Unit> getUnits() {
        return unitDAO.getUnits();
    }

    @Override
    public Unit getUnitByID(Long id) {
        return unitDAO.getUnitByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteUnitByID(Long id) {
        return unitDAO.deleteUnitByID(id);
    }

    @Override
    @Transactional
    public Unit saveUnit(Unit unit) {
        return unitDAO.saveUnit(unit);
    }

    @Override
    @Transactional
    public Unit updateUnit(Unit unit) {
        return unitDAO.updateUnit(unit);
    }
}
