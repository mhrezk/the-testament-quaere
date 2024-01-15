package com.testament.veltahleon.repositories.dao.implementation.religion.mythology;

import com.testament.veltahleon.model.entities.religion.mythology.Demon;
import com.testament.veltahleon.repositories.dao.ifc.religion.mythology.DemonDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class DemonDAOImpl implements DemonDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Demon> getDemons() {
        TypedQuery<Demon> query = entityManager.createQuery("FROM Demon", Demon.class);
        return query.getResultList();
    }

    @Override
    public Demon getDemonByID(Long id) {
        return entityManager.find(Demon.class, id);
    }

    @Override
    public Boolean deleteDemonByID(Long id) {
        Demon demon = entityManager.find(Demon.class, id);
        entityManager.remove(demon);
        return Boolean.TRUE;
    }

    @Override
    public Demon saveDemon(Demon demon) {
        entityManager.persist(demon);
        return demon;
    }

    @Override
    public Demon updateDemon(Demon demon) {
        return entityManager.merge(demon);
    }
}
