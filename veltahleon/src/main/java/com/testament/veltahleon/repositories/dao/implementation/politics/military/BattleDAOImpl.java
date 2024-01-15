package com.testament.veltahleon.repositories.dao.implementation.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Battle;
import com.testament.veltahleon.repositories.dao.ifc.politics.military.BattleDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class BattleDAOImpl implements BattleDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Battle> getBattles() {
        TypedQuery<Battle> query = entityManager.createQuery("FROM Battle", Battle.class);
        return query.getResultList();
    }

    @Override
    public Battle getBattleByID(Long id) {
        return entityManager.find(Battle.class, id);
    }

    @Override
    @Transactional
    public Boolean deleteBattleByID(Long id) {
        Battle battle = entityManager.find(Battle.class, id);
        entityManager.remove(battle);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Battle saveBattle(Battle battle) {
        entityManager.persist(battle);
        return battle;
    }

    @Override
    @Transactional
    public Battle updateBattle(Battle battle) {
        return entityManager.merge(battle);
    }

}
