package com.testament.veltahleon.dto.places;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CapitalDTO {
    private Long id;
    private String name;
    private String nation;
    private String history;
    private String flagURL;
}
