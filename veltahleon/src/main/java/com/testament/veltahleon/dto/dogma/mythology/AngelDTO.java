package com.testament.veltahleon.dto.dogma.mythology;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AngelDTO {
    private Long id;
    private String name;
    private String race;
    private String description;
    private String imageURL;
    private String powerDomain;
}
