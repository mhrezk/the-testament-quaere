package com.testament.veltahleon.services.entities.repo.implementation.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Unit;
import com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.politics.military.UnitRepository;
import com.testament.veltahleon.services.entities.repo.ifc.politics.military.UnitService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UnitServiceImpl implements UnitService {

    @Autowired
    private UnitRepository unitRepository;

    @Override
    public Collection<Unit> getUnitsWithPagination(int pageNumber, int numberOfRecords) {
        return unitRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Unit> getUnits() {
        return unitRepository.findAll();
    }

    @Override
    public Unit getUnitByID(Long id) {
        return unitRepository.findById(id).orElseThrow();
    }

    @Override
    public Unit getUnitByUnitType(String type) {
        return unitRepository.findByUnitType(type);
    }

    @Override
    public Boolean deleteUnitByID(Long id) {
        unitRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Unit saveUnit(Unit unit) {
        return unitRepository.save(unit);
    }

    @Override
    public Unit updateUnit(Long id, Unit unit) {
        Unit newUnit = unitRepository.findById(id).orElseThrow();

        if(unit.getUnitType() != null && newUnit.getUnitType() != unit.getUnitType()) {
            newUnit.setUnitType(unit.getUnitType());
        }

        if(unit.getDescription() != null && newUnit.getDescription() != unit.getDescription()) {
            newUnit.setDescription(unit.getDescription());
        }

        return unitRepository.save(newUnit);
    }
}
