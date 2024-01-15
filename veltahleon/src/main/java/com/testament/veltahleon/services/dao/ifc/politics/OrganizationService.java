package com.testament.veltahleon.services.dao.ifc.politics;

import com.testament.veltahleon.model.entities.politics.Organization;

import java.util.Collection;

public interface OrganizationService {

    Collection<Organization> getOrganizations();
    Organization getOrganizationByID(Long id);
    Boolean deleteOrganizationByID(Long id);
    Organization saveOrganization(Organization organization);
    Organization updateOrganization(Organization organization);
}
