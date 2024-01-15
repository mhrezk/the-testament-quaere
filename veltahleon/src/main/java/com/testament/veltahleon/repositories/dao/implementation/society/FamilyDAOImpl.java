package com.testament.veltahleon.repositories.dao.implementation.society;

import com.testament.veltahleon.model.entities.society.Family;
import com.testament.veltahleon.repositories.dao.ifc.society.FamilyDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class FamilyDAOImpl implements FamilyDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Family> getFamilies() {
        TypedQuery<Family> query = entityManager.createQuery("FROM Family", Family.class);
        return query.getResultList();
    }

    @Override
    public Family getFamilyByID(Long id) {
        return entityManager.find(Family.class, id);
    }

    @Override
    public Boolean deleteFamilyByID(Long id) {
        Family family = entityManager.find(Family.class, id);
        entityManager.remove(family);
        return Boolean.TRUE;
    }

    @Override
    public Family saveFamily(Family family) {
        entityManager.persist(family);
        return family;
    }

    @Override
    public Family updateFamily(Family family) {
        return entityManager.merge(family);
    }
}
