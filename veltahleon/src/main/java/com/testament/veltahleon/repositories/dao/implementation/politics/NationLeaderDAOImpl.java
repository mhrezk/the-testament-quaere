package com.testament.veltahleon.repositories.dao.implementation.politics;

import com.testament.veltahleon.model.entities.politics.NationLeader;
import com.testament.veltahleon.repositories.dao.ifc.politics.NationLeaderDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class NationLeaderDAOImpl implements NationLeaderDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<NationLeader> getNationLeaders() {
        TypedQuery<NationLeader> query = entityManager.createQuery("FROM NationLeader", NationLeader.class);
        return query.getResultList();
    }

    @Override
    public NationLeader getNationLeaderByID(Long id) {
        return entityManager.find(NationLeader.class, id);
    }

    @Override
    @Transactional
    public Boolean deleteNationLeaderByID(Long id) {
        NationLeader nationLeader = entityManager.find(NationLeader.class, id);
        entityManager.remove(nationLeader);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public NationLeader saveNationLeader(NationLeader nationLeader) {
        entityManager.persist(nationLeader);
        return nationLeader;
    }

    @Override
    @Transactional
    public NationLeader updateNationLeader(NationLeader nationLeader) {
        return entityManager.merge(nationLeader);
    }
}
