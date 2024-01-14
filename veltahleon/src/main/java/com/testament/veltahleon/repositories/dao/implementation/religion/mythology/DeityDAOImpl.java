package com.testament.veltahleon.repositories.dao.implementation.religion.mythology;

import com.testament.veltahleon.repositories.dao.ifc.religion.mythology.DeityDAO;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class DeityDAOImpl implements DeityDAO {

    @Autowired
    private EntityManager entityManager;
}
