package com.testament.veltahleon.repositories.dao.implementation.divination;

import com.testament.veltahleon.repositories.dao.ifc.divination.ConstellationDAO;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ConstellationDAOImpl implements ConstellationDAO {

    @Autowired
    private EntityManager entityManager;
}
