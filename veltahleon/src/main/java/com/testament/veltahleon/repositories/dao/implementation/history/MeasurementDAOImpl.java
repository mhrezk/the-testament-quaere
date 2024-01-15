package com.testament.veltahleon.repositories.dao.implementation.history;

import com.testament.veltahleon.model.entities.history.Measurement;
import com.testament.veltahleon.repositories.dao.ifc.history.MeasurementDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class MeasurementDAOImpl implements MeasurementDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Measurement> getMeasurements() {
        TypedQuery<Measurement> query = entityManager.createQuery("FROM Measurement", Measurement.class);
        return query.getResultList();
    }

    @Override
    public Measurement getMeasurementByID(Long id) {
        return entityManager.find(Measurement.class, id);
    }

    @Override
    @Transactional
    public Boolean deleteMeasurementByID(Long id) {
        Measurement measurement = entityManager.find(Measurement.class, id);
        entityManager.remove(measurement);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Measurement saveMeasurement(Measurement measurement) {
        entityManager.persist(measurement);
        return measurement;
    }

    @Override
    @Transactional
    public Measurement updateMeasurement(Measurement measurement) {
        return entityManager.merge(measurement);
    }
}
