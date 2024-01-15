package com.testament.veltahleon.repositories.dao.implementation.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Battalion;
import com.testament.veltahleon.repositories.dao.ifc.politics.military.BattalionDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class BattalionDAOImpl implements BattalionDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Battalion> getBattalions() {
        TypedQuery<Battalion> query = entityManager.createQuery("FROM Battalion", Battalion.class);
        return query.getResultList();
    }

    @Override
    public Battalion getBattalionByID(Long id) {
        return entityManager.find(Battalion.class, id);
    }

    @Override
    public Boolean deleteBattalionByID(Long id) {
        Battalion battalion = entityManager.find(Battalion.class, id);
        entityManager.remove(battalion);
        return Boolean.TRUE;
    }

    @Override
    public Battalion saveBattalion(Battalion battalion) {
        entityManager.persist(battalion);
        return battalion;
    }

    @Override
    public Battalion updateBattalion(Battalion battalion) {
        return entityManager.merge(battalion);
    }
}
