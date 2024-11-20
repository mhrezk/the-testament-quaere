package com.testament.veltahleon.dto.dogma;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProphetDTO {
    private Long id;
    private String name;
    private String description;
    private String religion;
    private String imageURL;
}
