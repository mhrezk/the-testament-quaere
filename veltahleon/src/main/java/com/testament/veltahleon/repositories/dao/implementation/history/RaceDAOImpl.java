package com.testament.veltahleon.repositories.dao.implementation.history;

import com.testament.veltahleon.model.entities.history.Race;
import com.testament.veltahleon.repositories.dao.ifc.history.RaceDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class RaceDAOImpl implements RaceDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Race> getRaces() {
        TypedQuery<Race> query = entityManager.createQuery("FROM Race", Race.class);
        return query.getResultList();
    }

    @Override
    public Race getRaceByID(Long id) {
        return entityManager.find(Race.class, id);
    }

    @Override
    public Boolean deleteRaceByID(Long id) {
        Race race = entityManager.find(Race.class, id);
        entityManager.remove(race);
        return Boolean.TRUE;
    }

    @Override
    public Race saveRace(Race race) {
        entityManager.persist(race);
        return race;
    }

    @Override
    public Race updateRace(Race race) {
        return entityManager.merge(race);
    }
}
