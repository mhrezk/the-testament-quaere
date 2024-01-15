package com.testament.veltahleon.repositories.dao.implementation.calendar;

import com.testament.veltahleon.model.entities.calendar.Year;
import com.testament.veltahleon.repositories.dao.ifc.calendar.YearDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class YearDAOImpl implements YearDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Year> getYears() {
        TypedQuery<Year> query = entityManager.createQuery("FROM Year", Year.class);
        return query.getResultList();
    }

    @Override
    public Year getYearByID(Long id) {
        return entityManager.find(Year.class, id);
    }

    @Override
    @Transactional
    public Boolean deleteYearByID(Long id) {
        Year year = entityManager.find(Year.class, id);
        entityManager.remove(year);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Year saveYear(Year year) {
        entityManager.persist(year);
        return year;
    }

    @Override
    @Transactional
    public Year updateYear(Year year) {
        return entityManager.merge(year);
    }
}
