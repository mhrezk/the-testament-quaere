package com.testament.veltahleon.repositories.dao.implementation.politics;

import com.testament.veltahleon.repositories.dao.ifc.places.NationDAO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NationLeaderDAOImpl implements NationDAO {

    @Autowired
    private EntityManager entityManager;
}
