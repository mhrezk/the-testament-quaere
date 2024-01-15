package com.testament.veltahleon.repositories.dao.implementation.calendar;

import com.testament.veltahleon.model.entities.calendar.Day;
import com.testament.veltahleon.repositories.dao.ifc.calendar.DayDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class DayDAOImpl implements DayDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Day> getDays() {
        TypedQuery<Day> query = entityManager.createQuery("FROM Day", Day.class);
        return query.getResultList();
    }

    @Override
    public Day getDayByID(Long id) {
        return entityManager.find(Day.class, id);
    }

    @Override
    @Transactional
    public Boolean deleteDayByID(Long id) {
        Day day = entityManager.find(Day.class, id);
        entityManager.remove(day);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Day saveDay(Day day) {
        entityManager.persist(day);
        return day;
    }

    @Override
    @Transactional
    public Day updateDay(Day day) {
        return entityManager.merge(day);
    }
}
