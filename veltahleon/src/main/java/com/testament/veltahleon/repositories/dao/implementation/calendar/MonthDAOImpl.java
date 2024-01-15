package com.testament.veltahleon.repositories.dao.implementation.calendar;

import com.testament.veltahleon.repositories.dao.ifc.calendar.MonthDAO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MonthDAOImpl implements MonthDAO {

    @Autowired
    private EntityManager entityManager;
}
