package com.testament.veltahleon.repositories.dao.implementation.politics.military;

import com.testament.veltahleon.model.entities.politics.military.ArmyLeader;
import com.testament.veltahleon.repositories.dao.ifc.politics.military.ArmyLeaderDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class ArmyLeaderDAOImpl implements ArmyLeaderDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<ArmyLeader> getArmyLeaders() {
        TypedQuery<ArmyLeader> query = entityManager.createQuery("FROM ArmyLeader", ArmyLeader.class);
        return query.getResultList();
    }

    @Override
    public ArmyLeader getArmyLeaderByID(Long id) {
        return entityManager.find(ArmyLeader.class, id);
    }

    @Override
    public Boolean deleteArmyLeaderByID(Long id) {
        ArmyLeader armyLeader = entityManager.find(ArmyLeader.class, id);
        entityManager.remove(armyLeader);
        return Boolean.TRUE;
    }

    @Override
    public ArmyLeader saveArmyLeader(ArmyLeader armyLeader) {
        entityManager.persist(armyLeader);
        return armyLeader;
    }

    @Override
    public ArmyLeader updateArmyLeader(ArmyLeader armyLeader) {
        return entityManager.merge(armyLeader);
    }
}
