package com.testament.veltahleon.repositories.dao.implementation.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Unit;
import com.testament.veltahleon.repositories.dao.ifc.politics.military.UnitDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class UnitDAOImpl implements UnitDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Unit> getUnits() {
        TypedQuery<Unit> query = entityManager.createQuery("FROM Unit", Unit.class);
        return query.getResultList();
    }

    @Override
    public Unit getUnitByID(Long id) {
        return entityManager.find(Unit.class, id);
    }

    @Override
    @Transactional
    public Boolean deleteUnitByID(Long id) {
        Unit unit = entityManager.find(Unit.class, id);
        entityManager.remove(unit);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Unit saveUnit(Unit unit) {
        entityManager.persist(unit);
        return unit;
    }

    @Override
    @Transactional
    public Unit updateUnit(Unit unit) {
        return entityManager.merge(unit);
    }
}
