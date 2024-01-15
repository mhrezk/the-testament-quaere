package com.testament.veltahleon.repositories.dao.implementation.places;

import com.testament.veltahleon.model.entities.places.Continent;
import com.testament.veltahleon.repositories.dao.ifc.places.ContinentDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class ContinentDAOImpl implements ContinentDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Continent> getContinents() {
        TypedQuery<Continent> query = entityManager.createQuery("FROM Continent", Continent.class);
        return query.getResultList();
    }

    @Override
    public Continent getContinentByID(Long id) {
        return entityManager.find(Continent.class, id);
    }

    @Override
    @Transactional
    public Boolean deleteContinentByID(Long id) {
        Continent continent = entityManager.find(Continent.class, id);
        entityManager.remove(continent);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Continent saveContinent(Continent continent) {
        entityManager.persist(continent);
        return continent;
    }

    @Override
    @Transactional
    public Continent updateContinent(Continent continent) {
        return entityManager.merge(continent);
    }
}
