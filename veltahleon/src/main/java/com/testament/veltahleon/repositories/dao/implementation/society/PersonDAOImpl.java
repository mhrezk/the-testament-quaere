package com.testament.veltahleon.repositories.dao.implementation.society;

import com.testament.veltahleon.repositories.dao.ifc.society.PersonDAO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PersonDAOImpl implements PersonDAO {

    @Autowired
    private EntityManager entityManager;
}
