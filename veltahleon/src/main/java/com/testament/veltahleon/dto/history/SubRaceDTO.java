package com.testament.veltahleon.dto.history;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubRaceDTO {
    private Long id;
    private String name;
    private String description;
    private String imageURL;
    private String race;
}
