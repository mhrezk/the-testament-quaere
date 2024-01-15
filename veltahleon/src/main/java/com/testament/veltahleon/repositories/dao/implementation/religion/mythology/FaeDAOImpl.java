package com.testament.veltahleon.repositories.dao.implementation.religion.mythology;

import com.testament.veltahleon.model.entities.religion.mythology.Fae;
import com.testament.veltahleon.repositories.dao.ifc.religion.mythology.FaeDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class FaeDAOImpl implements FaeDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Fae> getFaes() {
        TypedQuery<Fae> query = entityManager.createQuery("FROM Fae", Fae.class);
        return query.getResultList();
    }

    @Override
    public Fae getFaeByID(Long id) {
        return entityManager.find(Fae.class, id);
    }

    @Override
    public Boolean deleteFaeByID(Long id) {
        Fae fae = entityManager.find(Fae.class, id);
        entityManager.remove(fae);
        return Boolean.TRUE;
    }

    @Override
    public Fae saveFae(Fae fae) {
        entityManager.persist(fae);
        return fae;
    }

    @Override
    public Fae updateFae(Fae fae) {
        return entityManager.merge(fae);
    }
}
