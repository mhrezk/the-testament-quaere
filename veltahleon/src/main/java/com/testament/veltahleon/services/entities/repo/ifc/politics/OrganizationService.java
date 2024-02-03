package com.testament.veltahleon.services.entities.repo.ifc.politics;

import com.testament.veltahleon.model.entities.politics.Organization;

import java.util.Collection;

public interface OrganizationService {

    Collection<Organization> getOrganizationsWithPagination(int pageNumber, int numberOfRecords);
    Collection<Organization> getOrganizations();
    Organization getOrganizationByID(Long id);
    Organization getOrganizationByName(String name);
    Boolean deleteOrganizationByID(Long id);
    Organization saveOrganization(Organization organization);
    Organization updateOrganization(Long id, Organization organization);
}
