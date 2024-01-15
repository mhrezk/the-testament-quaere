package com.testament.veltahleon.repositories.dao.implementation.religion.mythology;

import com.testament.veltahleon.model.entities.religion.mythology.Deity;
import com.testament.veltahleon.repositories.dao.ifc.religion.mythology.DeityDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class DeityDAOImpl implements DeityDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Deity> getDeities() {
        TypedQuery<Deity> query = entityManager.createQuery("FROM Deity", Deity.class);
        return query.getResultList();
    }

    @Override
    public Deity getDeityByID(Long id) {
        return entityManager.find(Deity.class, id);
    }

    @Override
    @Transactional
    public Boolean deleteDeityByID(Long id) {
        Deity deity = entityManager.find(Deity.class, id);
        entityManager.remove(deity);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Deity saveDeity(Deity deity) {
        entityManager.persist(deity);
        return deity;
    }

    @Override
    @Transactional
    public Deity updateDeity(Deity deity) {
        return entityManager.merge(deity);
    }
}
