package com.testament.veltahleon.repositories.dao.implementation.politics.military;

import com.testament.veltahleon.repositories.dao.ifc.politics.military.ArmyDAO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ArmyDAOImpl implements ArmyDAO {

    @Autowired
    private EntityManager entityManager;
}
