package com.testament.veltahleon.services.entities.dao.implementation.politics;

import com.testament.veltahleon.model.entities.politics.Organization;
import com.testament.veltahleon.repositories.dao.ifc.politics.OrganizationDAO;
import com.testament.veltahleon.services.entities.dao.ifc.politics.OrganizationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationDAO organizationDAO;

    @Override
    public Collection<Organization> getOrganizations() {
        return organizationDAO.getOrganizations();
    }

    @Override
    public Organization getOrganizationByID(Long id) {
        return organizationDAO.getOrganizationByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteOrganizationByID(Long id) {
        return organizationDAO.deleteOrganizationByID(id);
    }

    @Override
    @Transactional
    public Organization saveOrganization(Organization organization) {
        return organizationDAO.saveOrganization(organization);
    }

    @Override
    @Transactional
    public Organization updateOrganization(Organization organization) {
        return organizationDAO.updateOrganization(organization);
    }
}
