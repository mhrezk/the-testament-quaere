package com.testament.veltahleon.dto.calendar;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CalendarSystemDTO {
    private Long id;
    private String name;
    private List<String> days;
    private List<String> months;
    //private List<Integer> epochs;
}
