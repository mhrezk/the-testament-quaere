package com.testament.veltahleon.repositories.dao.implementation.divination;

import com.testament.veltahleon.repositories.dao.ifc.divination.TarotDAO;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class TarotDAOImpl implements TarotDAO {

    @Autowired
    private EntityManager entityManager;
}
