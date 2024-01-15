package com.testament.veltahleon.repositories.dao.implementation.calendar;

import com.testament.veltahleon.repositories.dao.ifc.calendar.DayDAO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DayDAOImpl implements DayDAO {

    @Autowired
    private EntityManager entityManager;
}
