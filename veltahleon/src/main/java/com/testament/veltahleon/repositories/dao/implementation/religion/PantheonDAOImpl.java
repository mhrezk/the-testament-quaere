package com.testament.veltahleon.repositories.dao.implementation.religion;

import com.testament.veltahleon.model.entities.religion.Pantheon;
import com.testament.veltahleon.repositories.dao.ifc.religion.PantheonDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
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
    @Transactional
    public Boolean deletePantheonByID(Long id) {
        Pantheon pantheon = entityManager.find(Pantheon.class, id);
        entityManager.remove(pantheon);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Pantheon savePantheon(Pantheon pantheon) {
        entityManager.persist(pantheon);
        return pantheon;
    }

    @Override
    @Transactional
    public Pantheon updatePantheon(Pantheon pantheon) {
        return entityManager.merge(pantheon);
    }
}
