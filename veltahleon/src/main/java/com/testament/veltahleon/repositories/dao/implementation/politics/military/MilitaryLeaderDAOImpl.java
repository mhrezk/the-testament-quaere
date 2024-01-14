package com.testament.veltahleon.repositories.dao.implementation.politics.military;

import com.testament.veltahleon.repositories.dao.ifc.politics.military.MilitaryLeaderDAO;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MilitaryLeaderDAOImpl implements MilitaryLeaderDAO {

    @Autowired
    private EntityManager entityManager;
}
