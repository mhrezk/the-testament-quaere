package com.testament.veltahleon.services.entities.dao.ifc.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Unit;

import java.util.Collection;

public interface UnitService {

    Collection<Unit> getUnits();
    Unit getUnitByID(Long id);
    Boolean deleteUnitByID(Long id);
    Unit saveUnit(Unit unit);
    Unit updateUnit(Unit unit);
}
