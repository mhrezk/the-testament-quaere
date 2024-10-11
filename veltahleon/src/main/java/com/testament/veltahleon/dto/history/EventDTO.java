package com.testament.veltahleon.dto.history;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventDTO {
    private Long id;
    private String incident;
    private Integer eventDay;
    private Integer eventMonth;
    private Integer eventYear;
    private String timeline;
    private String yearAbbreviation;
    private String description;
}
