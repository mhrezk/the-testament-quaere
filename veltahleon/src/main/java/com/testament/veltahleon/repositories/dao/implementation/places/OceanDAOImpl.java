package com.testament.veltahleon.repositories.dao.implementation.places;

import com.testament.veltahleon.repositories.dao.ifc.places.OceanDAO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OceanDAOImpl implements OceanDAO {

    @Autowired
    private EntityManager entityManager;
}
