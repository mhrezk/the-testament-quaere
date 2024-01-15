package com.testament.veltahleon.repositories.dao.implementation.places;

import com.testament.veltahleon.model.entities.places.Nation;
import com.testament.veltahleon.repositories.dao.ifc.places.NationDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class NationDAOImpl implements NationDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Nation> getNations() {
        TypedQuery<Nation> query = entityManager.createQuery("FROM Nation", Nation.class);
        return query.getResultList();
    }

    @Override
    public Nation getNationByID(Long id) {
        return entityManager.find(Nation.class, id);
    }

    @Override
    public Boolean deleteNationByID(Long id) {
        Nation nation = entityManager.find(Nation.class, id);
        entityManager.remove(nation);
        return Boolean.TRUE;
    }

    @Override
    public Nation saveNation(Nation nation) {
        entityManager.persist(nation);
        return nation;
    }

    @Override
    public Nation updateNation(Nation nation) {
        return entityManager.merge(nation);
    }
}
