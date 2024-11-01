package com.testament.veltahleon.dto.places;

import lombok.*;

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
    private String capital;
    private Integer provincialNumber;
    private String description;
    private String flagURL;
}
