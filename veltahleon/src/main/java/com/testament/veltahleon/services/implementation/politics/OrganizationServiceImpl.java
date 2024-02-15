package com.testament.veltahleon.services.implementation.politics;

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
        return organizationRepository.findByFounder_Name(name);
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

        if(organization.getYearFoundationAndDisbandment() != null && newOrganization.getYearFoundationAndDisbandment() != organization.getYearFoundationAndDisbandment()) {
            newOrganization.setYearFoundationAndDisbandment(organization.getYearFoundationAndDisbandment());
        }

        if(organization.getUrlSymbol() != null && newOrganization.getUrlSymbol() != organization.getUrlSymbol()) {
            newOrganization.setUrlSymbol(organization.getUrlSymbol());
        }

        return organizationRepository.save(newOrganization);
    }
}
