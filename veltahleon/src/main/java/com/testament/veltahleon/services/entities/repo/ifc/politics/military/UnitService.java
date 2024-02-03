package com.testament.veltahleon.services.entities.repo.ifc.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Unit;

import java.util.Collection;

public interface UnitService {

    Collection<Unit> getUnitsWithPagination(int pageNumber, int numberOfRecords);
    Collection<Unit> getUnits();
    Unit getUnitByID(Long id);
    Boolean deleteUnitByID(Long id);
    Unit saveUnit(Unit unit);
    Unit updateUnit(Long id, Unit unit);
}
