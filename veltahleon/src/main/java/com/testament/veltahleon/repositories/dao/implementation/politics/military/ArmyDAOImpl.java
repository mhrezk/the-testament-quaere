package com.testament.veltahleon.repositories.dao.implementation.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Army;
import com.testament.veltahleon.repositories.dao.ifc.politics.military.ArmyDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class ArmyDAOImpl implements ArmyDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Army> getArmies() {
        TypedQuery<Army> query = entityManager.createQuery("FROM Army", Army.class);
        return query.getResultList();
    }

    @Override
    public Army getArmyByID(Long id) {
        return entityManager.find(Army.class, id);
    }

    @Override
    @Transactional
    public Boolean deleteArmyByID(Long id) {
        Army army = entityManager.find(Army.class, id);
        entityManager.remove(army);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Army saveArmy(Army army) {
        entityManager.persist(army);
        return army;
    }

    @Override
    @Transactional
    public Army updateArmy(Army army) {
        return entityManager.merge(army);
    }
}
