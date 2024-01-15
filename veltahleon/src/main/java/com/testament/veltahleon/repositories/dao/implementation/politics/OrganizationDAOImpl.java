package com.testament.veltahleon.repositories.dao.implementation.politics;

import com.testament.veltahleon.repositories.dao.ifc.politics.OrganizationDAO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrganizationDAOImpl implements OrganizationDAO {

    @Autowired
    private EntityManager entityManager;
}
