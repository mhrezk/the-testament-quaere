package com.testament.veltahleon.mappers.places;

import com.testament.veltahleon.dto.places.NationDetailsDTO;
import com.testament.veltahleon.enumerations.GovernanceType;
import com.testament.veltahleon.enumerations.NationStatus;
import com.testament.veltahleon.enumerations.NationType;
import com.testament.veltahleon.facades.places.NationFacade;
import com.testament.veltahleon.model.entities.history.Language;
import com.testament.veltahleon.model.entities.places.Nation;
import com.testament.veltahleon.model.entities.places.NationDetails;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NationDetailsMapper {

    @Autowired
    private NationFacade nationFacade;

    public NationDetailsDTO convertToDTO(NationDetails nationDetails) {
        return NationDetailsDTO.builder()
                .id(nationDetails.getId())
                .leaderFirstName(nationDetails.getLeaderFirstName().toUpperCase())
                .leaderSecondName(nationDetails.getLeaderSecondName().toUpperCase())
                .type(String.valueOf(nationDetails.getNation().getType()))
                .governanceType(String.valueOf(nationDetails.getNation().getGovernanceType()))
                .nationStatus(String.valueOf(nationDetails.getNation().getNationStatus()))
                .foundingYear(nationDetails.getFoundingYear())
                .endingYear(nationDetails.getEndingYear())
                .provincialNumber(nationDetails.getProvincialNumber())
                .nation(nationDetails.getNation().getName().toUpperCase())
                .rulingFamily(nationDetails.getRulingFamily())
                .rulingPartyName(nationDetails.getRulingPartyName())
                .capital(nationDetails.getNation().getCapital().getName())
                .history(nationDetails.getHistory())
                .flagURL(nationDetails.getFlagURL())
                .precedingNation(nationDetails.getPrecedingNation())
                .succeedingNation(nationDetails.getSucceedingNation())
                .languages(nationDetails.getLanguages().stream().map(Language::getName).toList())
                .build();
    }

    public NationDetails convertToEntity(NationDetailsDTO nationDetailsDTO) {
        NationDetails nationDetails = new NationDetails();
        nationDetails.setId(nationDetailsDTO.getId());
        Nation nation = new Nation();
        nation.setName(nationDetailsDTO.getNation().toUpperCase());
        nation.setCapital(nationFacade.getCapital(nationDetailsDTO.getCapital().toUpperCase()));
        nation.setType(NationType.valueOf(nationDetailsDTO.getType()));
        nation.setGovernanceType(GovernanceType.valueOf(nationDetailsDTO.getGovernanceType()));
        nation.setNationStatus(NationStatus.valueOf(nationDetailsDTO.getNationStatus()));
        nationDetails.setNation(nation);
        nationDetails.setHistory(nationDetailsDTO.getHistory());
        nationDetails.setLeaderSecondName(nationDetailsDTO.getLeaderSecondName());
        nationDetails.setLeaderFirstName(nationDetailsDTO.getLeaderFirstName());
        nationDetails.setFlagURL(nationDetailsDTO.getFlagURL());
        nationDetails.setRulingPartyName(nationDetailsDTO.getRulingPartyName());
        nationDetails.setProvincialNumber(nationDetailsDTO.getProvincialNumber());
        nationDetails.setRulingFamily(nationDetailsDTO.getRulingFamily());
        nationDetails.setFoundingYear(nationDetailsDTO.getFoundingYear());
        nationDetails.setEndingYear(nationDetailsDTO.getEndingYear());
        nationDetails.setPrecedingNation(nationDetailsDTO.getPrecedingNation());
        nationDetails.setSucceedingNation(nationDetailsDTO.getSucceedingNation());
        nationDetails.setLanguages(nationFacade.getLanguages(nationDetailsDTO.getLanguages()));
        return nationDetails;
    }
}
