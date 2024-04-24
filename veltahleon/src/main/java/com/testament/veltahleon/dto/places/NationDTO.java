package com.testament.veltahleon.dto.places;

import lombok.*;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NationDTO {
    private Long id;
    private String name;
    private String capital;
    private String leader;
    private String language;
    private Set<String> provinces;
    private String type;
    private String governanceType;
    private String description;
    private String urlFlag;
}
