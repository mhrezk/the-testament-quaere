package com.testament.veltahleon.repositories.dao.implementation.religion.mythology;

import com.testament.veltahleon.model.entities.religion.mythology.Pantheon;
import com.testament.veltahleon.repositories.dao.ifc.religion.mythology.PantheonDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class PantheonDAOImpl implements PantheonDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Pantheon> getPantheons() {
        TypedQuery<Pantheon> query = entityManager.createQuery("FROM Pantheon", Pantheon.class);
        return query.getResultList();
    }

    @Override
    public Pantheon getPantheonByID(Long id) {
        return entityManager.find(Pantheon.class, id);
    }

    @Override
    public Boolean deletePantheonByID(Long id) {
        Pantheon pantheon = entityManager.find(Pantheon.class, id);
        entityManager.remove(pantheon);
        return Boolean.TRUE;
    }

    @Override
    public Pantheon savePantheon(Pantheon pantheon) {
        entityManager.persist(pantheon);
        return pantheon;
    }

    @Override
    public Pantheon updatePantheon(Pantheon pantheon) {
        return entityManager.merge(pantheon);
    }
}