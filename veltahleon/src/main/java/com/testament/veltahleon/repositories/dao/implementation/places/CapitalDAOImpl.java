package com.testament.veltahleon.repositories.dao.implementation.places;

import com.testament.veltahleon.model.entities.places.Capital;
import com.testament.veltahleon.repositories.dao.ifc.places.CapitalDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class CapitalDAOImpl implements CapitalDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Capital> getCapitals() {
        TypedQuery<Capital> query = entityManager.createQuery("FROM Capital", Capital.class);
        return query.getResultList();
    }

    @Override
    public Capital getCapitalByID(Long id) {
        return entityManager.find(Capital.class, id);
    }

    @Override
    public Boolean deleteCapitalByID(Long id) {
        Capital capital = entityManager.find(Capital.class, id);
        entityManager.remove(capital);
        return Boolean.TRUE;
    }

    @Override
    public Capital saveCapital(Capital capital) {
        entityManager.persist(capital);
        return capital;
    }

    @Override
    public Capital updateCapital(Capital capital) {
        return entityManager.merge(capital);
    }
}
