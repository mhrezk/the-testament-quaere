package com.testament.veltahleon.repositories.dao.implementation.calendar;

import com.testament.veltahleon.model.entities.calendar.Epoch;
import com.testament.veltahleon.repositories.dao.ifc.calendar.EpochDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class EpochDAOImpl implements EpochDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Epoch> getEpochs() {
        TypedQuery<Epoch> query = entityManager.createQuery("FROM Epoch", Epoch.class);
        return query.getResultList();
    }

    @Override
    public Epoch getEpochByID(Long id) {
        return entityManager.find(Epoch.class, id);
    }

    @Override
    public Boolean deleteEpochByID(Long id) {
        Epoch epoch = entityManager.find(Epoch.class, id);
        entityManager.remove(epoch);
        return Boolean.TRUE;
    }

    @Override
    public Epoch saveEpoch(Epoch epoch) {
        entityManager.persist(epoch);
        return epoch;
    }

    @Override
    public Epoch updateEpoch(Epoch epoch) {
        return entityManager.merge(epoch);
    }
}
