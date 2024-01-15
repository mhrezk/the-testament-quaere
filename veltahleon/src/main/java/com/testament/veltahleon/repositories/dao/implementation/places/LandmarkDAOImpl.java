package com.testament.veltahleon.repositories.dao.implementation.places;

import com.testament.veltahleon.repositories.dao.ifc.places.LandmarkDAO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LandmarkDAOImpl implements LandmarkDAO {

    @Autowired
    private EntityManager entityManager;
}
