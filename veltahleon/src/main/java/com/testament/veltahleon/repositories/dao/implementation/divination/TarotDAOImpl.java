package com.testament.veltahleon.repositories.dao.implementation.divination;

import com.testament.veltahleon.repositories.dao.ifc.divination.TarotDAO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TarotDAOImpl implements TarotDAO {

    @Autowired
    private EntityManager entityManager;
}
