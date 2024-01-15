package com.testament.veltahleon.repositories.dao.implementation.places;

import com.testament.veltahleon.model.entities.places.Landmark;
import com.testament.veltahleon.repositories.dao.ifc.places.LandmarkDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class LandmarkDAOImpl implements LandmarkDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Landmark> getLandmarks() {
        TypedQuery<Landmark> query = entityManager.createQuery("FROM Landmark", Landmark.class);
        return query.getResultList();
    }

    @Override
    public Landmark getLandmarkByID(Long id) {
        return entityManager.find(Landmark.class, id);
    }

    @Override
    public Boolean deleteLandmarkByID(Long id) {
        Landmark landmark = entityManager.find(Landmark.class, id);
        entityManager.remove(landmark);
        return Boolean.TRUE;
    }

    @Override
    public Landmark saveLandmark(Landmark landmark) {
        entityManager.persist(landmark);
        return landmark;
    }

    @Override
    public Landmark updateLandmark(Landmark landmark) {
        return entityManager.merge(landmark);
    }
}
