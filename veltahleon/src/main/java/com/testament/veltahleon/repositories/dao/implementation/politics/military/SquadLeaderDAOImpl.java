package com.testament.veltahleon.repositories.dao.implementation.politics.military;

import com.testament.veltahleon.model.entities.politics.military.SquadLeader;
import com.testament.veltahleon.repositories.dao.ifc.politics.military.SquadLeaderDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class SquadLeaderDAOImpl implements SquadLeaderDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<SquadLeader> getSquadLeaders() {
        TypedQuery<SquadLeader> query = entityManager.createQuery("FROM SquadLeader", SquadLeader.class);
        return query.getResultList();
    }

    @Override
    public SquadLeader getSquadLeaderByID(Long id) {
        return entityManager.find(SquadLeader.class, id);
    }

    @Override
    @Transactional
    public Boolean deleteSquadLeaderByID(Long id) {
        SquadLeader squadLeader = entityManager.find(SquadLeader.class, id);
        entityManager.remove(squadLeader);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public SquadLeader saveSquadLeader(SquadLeader squadLeader) {
        entityManager.persist(squadLeader);
        return squadLeader;
    }

    @Override
    @Transactional
    public SquadLeader updateSquadLeader(SquadLeader squadLeader) {
        return entityManager.merge(squadLeader);
    }
}
