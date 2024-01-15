package com.testament.veltahleon.repositories.dao.implementation.places;

import com.testament.veltahleon.repositories.dao.ifc.places.NationDAO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NationTypeDAOImpl implements NationDAO {

    @Autowired
    private EntityManager entityManager;
}
