package com.testament.veltahleon.dto.history;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TimelineDTO {
    private Long id;
    private String name;
    private List<Long> events;
}
