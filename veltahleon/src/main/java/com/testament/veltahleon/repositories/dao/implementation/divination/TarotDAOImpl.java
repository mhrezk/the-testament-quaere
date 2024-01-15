package com.testament.veltahleon.repositories.dao.implementation.divination;

import com.testament.veltahleon.model.entities.divination.Tarot;
import com.testament.veltahleon.repositories.dao.ifc.divination.TarotDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class TarotDAOImpl implements TarotDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Tarot> getTarots() {
        TypedQuery<Tarot> query = entityManager.createQuery("FROM Tarot", Tarot.class);
        return query.getResultList();
    }

    @Override
    public Tarot getTarotByID(Long id) {
        return entityManager.find(Tarot.class, id);
    }

    @Override
    @Transactional
    public Boolean deleteTarotByID(Long id) {
        Tarot tarot = entityManager.find(Tarot.class, id);
        entityManager.remove(tarot);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Tarot saveTarot(Tarot tarot) {
        entityManager.persist(tarot);
        return tarot;
    }

    @Override
    @Transactional
    public Tarot updateTarot(Tarot tarot) {
        return entityManager.merge(tarot);
    }
}
