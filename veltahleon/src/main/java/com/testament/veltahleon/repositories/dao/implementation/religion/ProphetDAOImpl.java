package com.testament.veltahleon.repositories.dao.implementation.religion;

import com.testament.veltahleon.repositories.dao.ifc.religion.ProphetDAO;
import com.testament.veltahleon.repositories.dao.ifc.religion.ReligionDAO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProphetDAOImpl implements ProphetDAO {

    @Autowired
    private EntityManager entityManager;
}
