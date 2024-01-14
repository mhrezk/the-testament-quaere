package com.testament.veltahleon.repositories.dao.implementation.religion.mythology;

import com.testament.veltahleon.repositories.dao.ifc.religion.mythology.AngelDAO;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class AngelDAOImpl implements AngelDAO {

    @Autowired
    private EntityManager entityManager;
}
