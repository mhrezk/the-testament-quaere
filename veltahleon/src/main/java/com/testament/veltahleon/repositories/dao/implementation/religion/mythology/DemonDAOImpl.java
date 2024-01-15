package com.testament.veltahleon.repositories.dao.implementation.religion.mythology;

import com.testament.veltahleon.repositories.dao.ifc.religion.mythology.DemonDAO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DemonDAOImpl implements DemonDAO {

    @Autowired
    private EntityManager entityManager;
}
