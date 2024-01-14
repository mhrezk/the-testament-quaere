package com.testament.veltahleon.repositories.dao.implementation.society;

import com.testament.veltahleon.repositories.dao.ifc.society.TitleDAO;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class TitleDAOImpl implements TitleDAO {

    @Autowired
    private EntityManager entityManager;
}
