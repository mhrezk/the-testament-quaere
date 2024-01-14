package com.testament.veltahleon.repositories.dao.implementation.religion;

import com.testament.veltahleon.repositories.dao.ifc.religion.PantheonDAO;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class PantheonDAOImpl implements PantheonDAO {

    @Autowired
    private EntityManager entityManager;
}
