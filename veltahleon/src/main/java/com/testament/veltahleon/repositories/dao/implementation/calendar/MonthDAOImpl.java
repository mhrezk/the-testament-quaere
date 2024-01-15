package com.testament.veltahleon.repositories.dao.implementation.calendar;

import com.testament.veltahleon.model.entities.calendar.Month;
import com.testament.veltahleon.repositories.dao.ifc.calendar.MonthDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class MonthDAOImpl implements MonthDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Month> getMonths() {
        TypedQuery<Month> query = entityManager.createQuery("FROM Month", Month.class);
        return query.getResultList();
    }

    @Override
    public Month getMonthByID(Long id) {
        return entityManager.find(Month.class, id);
    }

    @Override
    @Transactional
    public Boolean deleteMonthByID(Long id) {
        Month month = entityManager.find(Month.class, id);
        entityManager.remove(month);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Month saveMonth(Month month) {
        entityManager.persist(month);
        return month;
    }

    @Override
    @Transactional
    public Month updateMonth(Month month) {
        return entityManager.merge(month);
    }
}
