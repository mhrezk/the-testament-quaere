package com.testament.veltahleon.repositories.dao.implementation.places;

import com.testament.veltahleon.repositories.dao.ifc.places.LocationDAO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LocationDAOImpl implements LocationDAO {

    @Autowired
    private EntityManager entityManager;
}
