package com.testament.veltahleon.repositories.dao.implementation.religion.mythology;

import com.testament.veltahleon.repositories.dao.ifc.religion.mythology.FaeDAO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FaeDAOImpl implements FaeDAO {

    @Autowired
    private EntityManager entityManager;
}
