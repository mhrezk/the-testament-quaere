package com.testament.veltahleon.repositories.dao.implementation.places;

import com.testament.veltahleon.repositories.dao.ifc.places.ProvinceDAO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProvinceDAOImpl implements ProvinceDAO {

    @Autowired
    private EntityManager entityManager;
}
