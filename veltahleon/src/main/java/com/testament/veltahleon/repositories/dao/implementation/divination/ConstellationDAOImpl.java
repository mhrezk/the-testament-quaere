package com.testament.veltahleon.repositories.dao.implementation.divination;

import com.testament.veltahleon.model.entities.divination.Constellation;
import com.testament.veltahleon.repositories.dao.ifc.divination.ConstellationDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class ConstellationDAOImpl implements ConstellationDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Constellation> getConstellations() {
        TypedQuery<Constellation> query = entityManager.createQuery("FROM Constellation", Constellation.class);
        return query.getResultList();
    }

    @Override
    public Constellation getConstellationByID(Long id) {
        return entityManager.find(Constellation.class, id);
    }

    @Override
    public Boolean deleteConstellationByID(Long id) {
        Constellation constellation = entityManager.find(Constellation.class, id);
        entityManager.remove(constellation);
        return Boolean.TRUE;
    }

    @Override
    public Constellation saveConstellation(Constellation constellation) {
        entityManager.persist(constellation);
        return constellation;
    }

    @Override
    public Constellation updateConstellation(Constellation constellation) {
        return entityManager.merge(constellation);
    }
}
