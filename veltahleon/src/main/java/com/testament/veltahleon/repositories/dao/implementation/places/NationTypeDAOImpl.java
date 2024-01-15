package com.testament.veltahleon.repositories.dao.implementation.places;

import com.testament.veltahleon.model.entities.places.NationType;
import com.testament.veltahleon.repositories.dao.ifc.places.NationTypeDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class NationTypeDAOImpl implements NationTypeDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<NationType> getNationTypes() {
        TypedQuery<NationType> query = entityManager.createQuery("FROM NationType", NationType.class);
        return query.getResultList();
    }

    @Override
    public NationType getNationTypeByID(Long id) {
        return entityManager.find(NationType.class, id);
    }

    @Override
    public Boolean deleteNationTypeByID(Long id) {
        NationType nationType = entityManager.find(NationType.class, id);
        entityManager.remove(nationType);
        return Boolean.TRUE;
    }

    @Override
    public NationType saveNationType(NationType nationType) {
        entityManager.persist(nationType);
        return nationType;
    }

    @Override
    public NationType updateNationType(NationType nationType) {
        return entityManager.merge(nationType);
    }
}
