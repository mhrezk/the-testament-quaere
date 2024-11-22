package com.testament.veltahleon.mappers.politics;

import com.testament.veltahleon.dto.politics.OrganizationDTO;
import com.testament.veltahleon.facades.places.NationFacade;
import com.testament.veltahleon.model.entities.politics.Organization;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrganizationMapper {

    @Autowired
    private NationFacade nationFacade;

    public OrganizationDTO convertToDTO(Organization organization) {
        return OrganizationDTO.builder()
                .id(organization.getId())
                .name(organization.getName())
                .founderFirstName(organization.getFounderFirstName())
                .founderSecondName(organization.getFounderSecondName())
                .symbolURL(organization.getSymbolURL())
                .foundationDay(organization.getFoundationDay())
                .foundationMonth(organization.getFoundationMonth())
                .foundationYear(organization.getFoundationYear())
                .disbandmentDay(organization.getDisbandmentDay())
                .disbandmentMonth(organization.getDisbandmentMonth())
                .disbandmentYear(organization.getDisbandmentYear())
                .build();
    }

    public Organization convertToEntity(OrganizationDTO organizationDTO) {
        Organization organization = new Organization();
        organization.setSymbolURL(organizationDTO.getSymbolURL());
        organization.setName(organizationDTO.getName());
        organization.setFounderFirstName(organizationDTO.getFounderFirstName());
        organization.setFounderSecondName(organizationDTO.getFounderSecondName());
        organization.setFoundationDay(organizationDTO.getFoundationDay());
        organization.setFoundationMonth(organizationDTO.getFoundationMonth());
        organization.setFoundationYear(organizationDTO.getFoundationYear());
        organization.setDisbandmentDay(organizationDTO.getDisbandmentDay());
        organization.setDisbandmentMonth(organizationDTO.getDisbandmentMonth());
        organization.setDisbandmentYear(organizationDTO.getDisbandmentYear());
        return organization;
    }
}
