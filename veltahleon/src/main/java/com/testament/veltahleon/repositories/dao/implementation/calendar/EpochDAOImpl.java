package com.testament.veltahleon.repositories.dao.implementation.calendar;

import com.testament.veltahleon.repositories.dao.ifc.calendar.EpochDAO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EpochDAOImpl implements EpochDAO {

    @Autowired
    private EntityManager entityManager;
}
