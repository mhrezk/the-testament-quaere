package com.testament.veltahleon.repositories.dao.implementation.politics;

import com.testament.veltahleon.model.entities.politics.Organization;
import com.testament.veltahleon.repositories.dao.ifc.politics.OrganizationDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class OrganizationDAOImpl implements OrganizationDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Organization> getOrganizations() {
        TypedQuery<Organization> query = entityManager.createQuery("FROM Organization", Organization.class);
        return query.getResultList();
    }

    @Override
    public Organization getOrganizationByID(Long id) {
        return entityManager.find(Organization.class, id);
    }

    @Override
    public Boolean deleteOrganizationByID(Long id) {
        Organization organization = entityManager.find(Organization.class, id);
        entityManager.remove(organization);
        return Boolean.TRUE;
    }

    @Override
    public Organization saveOrganization(Organization organization) {
        entityManager.persist(organization);
        return organization;
    }

    @Override
    public Organization updateOrganization(Organization organization) {
        return entityManager.merge(organization);
    }
}
