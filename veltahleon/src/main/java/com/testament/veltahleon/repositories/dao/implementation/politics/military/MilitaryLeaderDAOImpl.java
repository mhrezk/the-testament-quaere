package com.testament.veltahleon.repositories.dao.implementation.politics.military;

import com.testament.veltahleon.model.entities.politics.military.MilitaryLeader;
import com.testament.veltahleon.repositories.dao.ifc.politics.military.MilitaryLeaderDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class MilitaryLeaderDAOImpl implements MilitaryLeaderDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<MilitaryLeader> getMilitaryLeaders() {
        TypedQuery<MilitaryLeader> query = entityManager.createQuery("FROM MilitaryLeader", MilitaryLeader.class);
        return query.getResultList();
    }

    @Override
    public MilitaryLeader getMilitaryLeaderByID(Long id) {
        return entityManager.find(MilitaryLeader.class, id);
    }

    @Override
    @Transactional
    public Boolean deleteMilitaryLeaderByID(Long id) {
        MilitaryLeader militaryLeader = entityManager.find(MilitaryLeader.class, id);
        entityManager.remove(militaryLeader);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public MilitaryLeader saveMilitaryLeader(MilitaryLeader militaryLeader) {
        entityManager.persist(militaryLeader);
        return militaryLeader;
    }

    @Override
    @Transactional
    public MilitaryLeader updateMilitaryLeader(MilitaryLeader militaryLeader) {
        return entityManager.merge(militaryLeader);
    }
}
