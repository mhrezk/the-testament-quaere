package com.testament.veltahleon.repositories.dao.implementation.places;

import com.testament.veltahleon.model.entities.places.Ocean;
import com.testament.veltahleon.repositories.dao.ifc.places.OceanDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class OceanDAOImpl implements OceanDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Ocean> getOceans() {
        TypedQuery<Ocean> query = entityManager.createQuery("FROM Ocean", Ocean.class);
        return query.getResultList();
    }

    @Override
    public Ocean getOceanByID(Long id) {
        return entityManager.find(Ocean.class, id);
    }

    @Override
    public Boolean deleteOceanByID(Long id) {
        Ocean ocean = entityManager.find(Ocean.class, id);
        entityManager.remove(ocean);
        return Boolean.TRUE;
    }

    @Override
    public Ocean saveOcean(Ocean ocean) {
        entityManager.persist(ocean);
        return ocean;
    }

    @Override
    public Ocean updateOcean(Ocean ocean) {
        return entityManager.merge(ocean);
    }
}
