package com.testament.veltahleon.repositories.dao.implementation.history;

import com.testament.veltahleon.repositories.dao.ifc.history.LetterDAO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LetterDAOImpl implements LetterDAO {

    @Autowired
    private EntityManager entityManager;
}
