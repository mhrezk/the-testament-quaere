package com.testament.veltahleon.dto.calendar;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MonthDTO {
    private Long id;
    private String name;
    private Integer monthNumber;
    private Integer numberOfDays;
    private String description;
}
