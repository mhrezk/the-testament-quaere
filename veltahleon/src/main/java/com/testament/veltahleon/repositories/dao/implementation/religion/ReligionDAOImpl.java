package com.testament.veltahleon.repositories.dao.implementation.religion;

import com.testament.veltahleon.model.entities.religion.Religion;
import com.testament.veltahleon.repositories.dao.ifc.religion.ReligionDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class ReligionDAOImpl implements ReligionDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Religion> getReligions() {
        TypedQuery<Religion> query = entityManager.createQuery("FROM Religion", Religion.class);
        return query.getResultList();
    }

    @Override
    public Religion getReligionByID(Long id) {
        return entityManager.find(Religion.class, id);
    }

    @Override
    @Transactional
    public Boolean deleteReligionByID(Long id) {
        Religion religion = entityManager.find(Religion.class, id);
        entityManager.remove(religion);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Religion saveReligion(Religion religion) {
        entityManager.persist(religion);
        return religion;
    }

    @Override
    @Transactional
    public Religion updateReligion(Religion religion) {
        return entityManager.merge(religion);
    }
}
