package com.testament.veltahleon.repositories.dao.implementation.politics.military;

import com.testament.veltahleon.repositories.dao.ifc.politics.military.ArmyLeaderDAO;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ArmyLeaderDAOImpl implements ArmyLeaderDAO {

    @Autowired
    private EntityManager entityManager;
}
