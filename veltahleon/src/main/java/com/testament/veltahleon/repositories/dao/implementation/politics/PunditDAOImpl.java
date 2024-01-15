package com.testament.veltahleon.repositories.dao.implementation.politics;

import com.testament.veltahleon.model.entities.politics.Pundit;
import com.testament.veltahleon.repositories.dao.ifc.politics.PunditDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class PunditDAOImpl implements PunditDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Pundit> getPundits() {
        TypedQuery<Pundit> query = entityManager.createQuery("FROM Pundit", Pundit.class);
        return query.getResultList();
    }

    @Override
    public Pundit getPunditByID(Long id) {
        return entityManager.find(Pundit.class, id);
    }

    @Override
    @Transactional
    public Boolean deletePunditByID(Long id) {
        Pundit pundit = entityManager.find(Pundit.class, id);
        entityManager.remove(pundit);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Pundit savePundit(Pundit pundit) {
        entityManager.persist(pundit);
        return pundit;
    }

    @Override
    @Transactional
    public Pundit updatePundit(Pundit pundit) {
        return entityManager.merge(pundit);
    }
}
