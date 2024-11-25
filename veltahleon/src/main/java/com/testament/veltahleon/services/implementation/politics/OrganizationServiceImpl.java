package com.testament.veltahleon.services.implementation.politics;

import com.testament.veltahleon.exceptions.DataNotFoundException;
import com.testament.veltahleon.facades.places.NationFacade;
import com.testament.veltahleon.model.entities.politics.Organization;
import com.testament.veltahleon.repositories.politics.OrganizationRepository;
import com.testament.veltahleon.services.ifc.politics.OrganizationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private NationFacade nationFacade;


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
    public Boolean deleteOrganizationByID(Long id) {
        organizationRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Organization saveOrganization(Organization organization) {
        Organization newOrganization = generateOrganization(organization);
        return organizationRepository.save(newOrganization);
    }

    private String defaultImageURL(String imageName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/politics/organizations/images/" + imageName).toUriString();
    }

    private Organization generateOrganization(Organization organization) {
        return Organization.builder()
                .name(organization.getName().toUpperCase())
                .symbolURL(defaultImageURL("kaaba.png"))
                .foundationDay(0)
                .foundationMonth(0)
                .foundationYear(0)
                .foundationYearAbbreviation("N/A")
                .disbandmentDay(0)
                .disbandmentMonth(0)
                .disbandmentYear(0)
                .organizationSize(0)
                .founderFirstName("None".toUpperCase())
                .founderSecondName("None".toUpperCase())
                .disbandmentYearAbbreviation("N/A")
                .build();
    }

    @Override
    public Organization updateOrganization(Long id, Organization organization) {
        Organization newOrganization = organizationRepository.findById(id).orElseThrow();

        if(organization.getName() != null && newOrganization.getName() != organization.getName()) {
            newOrganization.setName(organization.getName().toUpperCase());
        }

        if(organization.getOrganizationSize() != null && newOrganization.getOrganizationSize() != organization.getOrganizationSize()) {
            newOrganization.setOrganizationSize(organization.getOrganizationSize());
        }

        if(organization.getFounderFirstName() != null && newOrganization.getFounderFirstName() != organization.getFounderFirstName()) {
            newOrganization.setFounderFirstName(organization.getFounderFirstName().toUpperCase());
        }

        if(organization.getFounderSecondName() != null && newOrganization.getFounderSecondName() != organization.getFounderSecondName()) {
            newOrganization.setFounderSecondName(organization.getFounderSecondName().toUpperCase());
        }

        if(organization.getFoundationDay() != null && newOrganization.getFoundationDay() != organization.getFoundationDay()) {
            newOrganization.setFoundationDay(organization.getFoundationDay());
        }

        if(organization.getFoundationMonth() != null && newOrganization.getFoundationMonth() != organization.getFoundationMonth()) {
            newOrganization.setFoundationMonth(organization.getFoundationMonth());
        }

        if(organization.getFoundationYear() != null && newOrganization.getFoundationYear() != organization.getFoundationYear()) {
            newOrganization.setFoundationYear(organization.getFoundationYear());
        }

        if(organization.getFoundationYearAbbreviation() != null && newOrganization.getFoundationYearAbbreviation() != organization.getFoundationYearAbbreviation()) {
            newOrganization.setFoundationYearAbbreviation(organization.getFoundationYearAbbreviation().toUpperCase());
        }

        if(organization.getDisbandmentDay() != null && newOrganization.getDisbandmentDay() != organization.getDisbandmentDay()) {
            newOrganization.setDisbandmentDay(organization.getDisbandmentDay());
        }

        if(organization.getDisbandmentMonth() != null && newOrganization.getDisbandmentMonth() != organization.getDisbandmentMonth()) {
            newOrganization.setDisbandmentMonth(organization.getDisbandmentMonth());
        }

        if(organization.getDisbandmentYear() != null && newOrganization.getDisbandmentYear() != organization.getDisbandmentYear()) {
            newOrganization.setDisbandmentYear(organization.getDisbandmentYear());
        }

        if(organization.getDisbandmentYearAbbreviation() != null && newOrganization.getDisbandmentYearAbbreviation() != organization.getDisbandmentYearAbbreviation()) {
            newOrganization.setDisbandmentYearAbbreviation(organization.getDisbandmentYearAbbreviation().toUpperCase());
        }

        if(organization.getSymbolURL() != null && newOrganization.getSymbolURL() != organization.getSymbolURL()) {
            newOrganization.setSymbolURL(organization.getSymbolURL());
        }

        if(organization.getDescription() != null && newOrganization.getDescription() != organization.getDescription()) {
            newOrganization.setDescription(organization.getDescription());
        }

        return organizationRepository.save(newOrganization);
    }

    @Override
    public Organization modifyOrganization(Long id, Organization organization) {
        Organization newOrganization = organizationRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Datum does not exist in database!"));
        newOrganization.setName(organization.getName().toUpperCase());
        newOrganization.setOrganizationSize(organization.getOrganizationSize());
        newOrganization.setSymbolURL(organization.getSymbolURL());
        newOrganization.setFoundationDay(organization.getFoundationDay());
        newOrganization.setFoundationMonth(organization.getFoundationMonth());
        newOrganization.setFoundationYear(organization.getFoundationYear());
        newOrganization.setFoundationYearAbbreviation(organization.getFoundationYearAbbreviation());
        newOrganization.setDisbandmentDay(organization.getDisbandmentDay());
        newOrganization.setDisbandmentMonth(organization.getDisbandmentMonth());
        newOrganization.setDisbandmentYear(organization.getDisbandmentYear());
        newOrganization.setDisbandmentYearAbbreviation(organization.getDisbandmentYearAbbreviation());
        newOrganization.setFounderFirstName(organization.getFounderFirstName().toUpperCase());
        newOrganization.setFounderSecondName(organization.getFounderSecondName().toUpperCase());
        return newOrganization;
    }

    @Override
    public long countOrganizations() {
        return organizationRepository.count();
    }
}
