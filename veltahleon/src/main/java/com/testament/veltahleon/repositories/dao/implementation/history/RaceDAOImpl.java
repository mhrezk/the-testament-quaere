package com.testament.veltahleon.repositories.dao.implementation.history;

import com.testament.veltahleon.repositories.dao.ifc.history.RaceDAO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RaceDAOImpl implements RaceDAO {

    @Autowired
    private EntityManager entityManager;
}
