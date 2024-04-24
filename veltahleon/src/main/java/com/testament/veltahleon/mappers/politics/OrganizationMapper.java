package com.testament.veltahleon.mappers.politics;

import com.testament.veltahleon.dto.politics.OrganizationDTO;
import com.testament.veltahleon.facades.politics.OrganizationFacade;
import com.testament.veltahleon.model.entities.politics.Organization;
import com.testament.veltahleon.services.ifc.calendar.YearService;
import com.testament.veltahleon.services.ifc.places.NationService;
import com.testament.veltahleon.services.ifc.politics.PunditService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrganizationMapper {

    @Autowired
    private OrganizationFacade organizationFacade;

    public OrganizationDTO convertToDTO(Organization organization) {
        return OrganizationDTO.builder()
                .id(organization.getId())
                .name(organization.getName())
                .founder(organization.getFounder().getName())
                .nation(organization.getNation().getName())
                .urlSymbol(organization.getUrlSymbol())
                .foundationDay(organization.getFoundationYear().getDay().getDayNumber())
                .foundationMonth(organization.getFoundationYear().getMonth().getMonthNumber())
                .foundationYear(organization.getFoundationYear().getEpoch().getYearNumber())
                .disbandmentDay(organization.getDisbandmentYear().getDay().getDayNumber())
                .disbandmentMonth(organization.getDisbandmentYear().getMonth().getMonthNumber())
                .disbandmentYear(organization.getDisbandmentYear().getEpoch().getYearNumber())
                .build();
    }

    public Organization convertToEntity(OrganizationDTO organizationDTO) {
        Organization organization = new Organization();
        String[] foundationDate = organizationDTO.getFoundationDate().split("/", 3);
        String[] disbandmentDate = organizationDTO.getDisbandmentDate().split("/", 3);
        organization.setUrlSymbol(organizationDTO.getUrlSymbol());
        organization.setName(organizationDTO.getName());
        organization.setNation(organizationFacade.getNation(organizationDTO.getNation()));
        organization.setFounder(organizationFacade.getPundit(organizationDTO.getFounder()));
        organization.setFoundationYear(organizationFacade.getYear(Integer.parseInt(foundationDate[0]), Integer.parseInt(foundationDate[1]), Integer.parseInt(foundationDate[2])));
        organization.setDisbandmentYear(organizationFacade.getYear(Integer.parseInt(disbandmentDate[0]), Integer.parseInt(disbandmentDate[1]), Integer.parseInt(disbandmentDate[2])));
        return organization;
    }
}
