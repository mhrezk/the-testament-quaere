package com.testament.veltahleon.repositories.dao.implementation.history.library;

import com.testament.veltahleon.repositories.dao.ifc.history.library.ChapterDAO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChapterDAOImpl implements ChapterDAO {

    @Autowired
    private EntityManager entityManager;
}
