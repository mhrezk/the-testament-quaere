package com.testament.veltahleon.dto.calendar;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class YearDTO {
    private Long id;
    private String name;
    private String day;
    private String month;
    private int epoch;
}
