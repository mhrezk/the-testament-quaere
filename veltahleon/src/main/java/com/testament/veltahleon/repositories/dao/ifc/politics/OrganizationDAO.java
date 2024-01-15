package com.testament.veltahleon.repositories.dao.ifc.politics;

import com.testament.veltahleon.model.entities.politics.Organization;

import java.util.Collection;

public interface OrganizationDAO {

    Collection<Organization> getOrganizations();
    Organization getOrganizationByID(Long id);
    Boolean deleteOrganizationByID(Long id);
    Organization saveOrganization(Organization organization);
    Organization updateOrganization(Organization organization);
}