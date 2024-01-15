package com.testament.veltahleon.repositories.dao.implementation.history.library;

import com.testament.veltahleon.repositories.dao.ifc.history.library.AuthorDAO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AuthorDAOImpl implements AuthorDAO {

    @Autowired
    private EntityManager entityManager;
}
