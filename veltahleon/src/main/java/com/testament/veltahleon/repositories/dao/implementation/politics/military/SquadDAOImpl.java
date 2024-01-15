package com.testament.veltahleon.repositories.dao.implementation.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Squad;
import com.testament.veltahleon.repositories.dao.ifc.politics.military.SquadDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class SquadDAOImpl implements SquadDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Squad> getSquads() {
        TypedQuery<Squad> query = entityManager.createQuery("FROM Squad", Squad.class);
        return query.getResultList();
    }

    @Override
    public Squad getSquadByID(Long id) {
        return entityManager.find(Squad.class, id);
    }

    @Override
    @Transactional
    public Boolean deleteSquadByID(Long id) {
        Squad squad = entityManager.find(Squad.class, id);
        entityManager.remove(squad);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Squad saveSquad(Squad squad) {
        entityManager.persist(squad);
        return squad;
    }

    @Override
    @Transactional
    public Squad updateSquad(Squad squad) {
        return entityManager.merge(squad);
    }
}
