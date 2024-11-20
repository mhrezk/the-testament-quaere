package com.testament.veltahleon.dto.dogma.mythology;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeityDTO {
    private Long id;
    private String name;
    private String description;
    private String religion;
    private String imageURL;
}
