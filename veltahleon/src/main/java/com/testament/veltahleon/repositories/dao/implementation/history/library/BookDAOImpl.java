package com.testament.veltahleon.repositories.dao.implementation.history.library;

import com.testament.veltahleon.repositories.dao.ifc.history.library.BookDAO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookDAOImpl implements BookDAO {

    @Autowired
    private EntityManager entityManager;
}
