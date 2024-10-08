package com.testament.veltahleon.services.implementation.politics;

import com.testament.veltahleon.exceptions.DataNotFoundException;
import com.testament.veltahleon.model.entities.politics.Organization;
import com.testament.veltahleon.repositories.politics.OrganizationRepository;
import com.testament.veltahleon.services.ifc.politics.OrganizationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;


    @Override
    public Collection<Organization> getOrganizationsWithPagination(int pageNumber, int numberOfRecords) {
        return organizationRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Organization> getOrganizations() {
        return organizationRepository.findAll();
    }

    @Override
    public Organization getOrganizationByID(Long id) {
        return organizationRepository.findById(id).orElseThrow();
    }

    @Override
    public Organization getOrganizationByName(String name) {
        return organizationRepository.findByName(name);
    }

    @Override
    public Organization getOrganizationByFounderName(String name) {
        return organizationRepository.findByFounder_FirstName(name);
    }

    @Override
    public Boolean deleteOrganizationByID(Long id) {
        organizationRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Organization saveOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }

    @Override
    public Organization updateOrganization(Long id, Organization organization) {
        Organization newOrganization = organizationRepository.findById(id).orElseThrow();

        if(organization.getName() != null && newOrganization.getName() != organization.getName()) {
            newOrganization.setName(organization.getName());
        }

        if(organization.getNation() != null && newOrganization.getNation() != organization.getNation()) {
            newOrganization.setNation(organization.getNation());
        }

        if(organization.getFounder() != null && newOrganization.getFounder() != organization.getFounder()) {
            newOrganization.setFounder(organization.getFounder());
        }

        if(organization.getFoundationYear() != null && newOrganization.getFoundationYear() != organization.getFoundationYear()) {
            newOrganization.setFoundationYear(organization.getFoundationYear());
        }

        if(organization.getDisbandmentYear() != null && newOrganization.getDisbandmentYear() != organization.getDisbandmentYear()) {
            newOrganization.setDisbandmentYear(organization.getDisbandmentYear());
        }

        if(organization.getUrlSymbol() != null && newOrganization.getUrlSymbol() != organization.getUrlSymbol()) {
            newOrganization.setUrlSymbol(organization.getUrlSymbol());
        }

        return organizationRepository.save(newOrganization);
    }

    @Override
    public Organization modifyOrganization(Long id, Organization organization) {
        Organization newOrganization = organizationRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Datum does not exist in database!"));

        newOrganization.setName(organization.getName());
        newOrganization.setUrlSymbol(organization.getUrlSymbol());
        newOrganization.setFoundationYear(organization.getFoundationYear());
        newOrganization.setDisbandmentYear(organization.getDisbandmentYear());
        newOrganization.setNation(organization.getNation());
        newOrganization.setFounder(organization.getFounder());

        return newOrganization;
    }
}
