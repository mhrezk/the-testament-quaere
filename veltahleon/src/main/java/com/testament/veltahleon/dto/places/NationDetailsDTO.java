package com.testament.veltahleon.dto.places;

import jakarta.persistence.Column;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NationDetailsDTO {
    private Long id;
    private String nation;
    private String leaderFirstName;
    private String leaderSecondName;
    private String rulingPartyName;
    private String rulingFamily;
    private String type;
    private String governanceType;
    private String nationStatus;
    private Integer foundingYear;
    private Integer endingYear;
    private String capital;
    private Integer provincialNumber;
    private String history;
    private String flagURL;
    private List<String> languages;
    private String precedingNation;
    private String succeedingNation;
}
