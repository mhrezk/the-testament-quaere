package com.testament.veltahleon.repositories.dao.implementation.religion;

import com.testament.veltahleon.model.entities.religion.Prophet;
import com.testament.veltahleon.repositories.dao.ifc.religion.ProphetDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class ProphetDAOImpl implements ProphetDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Prophet> getProphets() {
        TypedQuery<Prophet> query = entityManager.createQuery("FROM Prophet", Prophet.class);
        return query.getResultList();
    }

    @Override
    public Prophet getProphetByID(Long id) {
        return entityManager.find(Prophet.class, id);
    }

    @Override
    public Boolean deleteProphetByID(Long id) {
        Prophet prophet = entityManager.find(Prophet.class, id);
        entityManager.remove(prophet);
        return Boolean.TRUE;
    }

    @Override
    public Prophet saveProphet(Prophet prophet) {
        entityManager.persist(prophet);
        return prophet;
    }

    @Override
    public Prophet updateProphet(Prophet prophet) {
        return entityManager.merge(prophet);
    }
}
