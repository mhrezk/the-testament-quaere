package com.testament.veltahleon.repositories.dao.implementation.religion.mythology;

import com.testament.veltahleon.model.entities.religion.mythology.Angel;
import com.testament.veltahleon.repositories.dao.ifc.religion.mythology.AngelDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class AngelDAOImpl implements AngelDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Angel> getAngels() {
        TypedQuery<Angel> query = entityManager.createQuery("FROM Angel", Angel.class);
        return query.getResultList();
    }

    @Override
    public Angel getAngelByID(Long id) {
        return entityManager.find(Angel.class, id);
    }

    @Override
    public Boolean deleteAngelByID(Long id) {
        Angel angel = entityManager.find(Angel.class, id);
        entityManager.remove(angel);
        return Boolean.TRUE;
    }

    @Override
    public Angel saveAngel(Angel angel) {
        entityManager.persist(angel);
        return angel;
    }

    @Override
    public Angel updateAngel(Angel angel) {
        return entityManager.merge(angel);
    }
}
