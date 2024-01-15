package com.testament.veltahleon.repositories.dao.ifc.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Unit;

import java.util.Collection;

public interface UnitDAO {

    Collection<Unit> getUnits();
    Unit getUnitByID(Long id);
    Boolean deleteUnitByID(Long id);
    Unit saveUnit(Unit unit);
    Unit updateUnit(Unit unit);
}
