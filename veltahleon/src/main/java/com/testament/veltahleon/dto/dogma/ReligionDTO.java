package com.testament.veltahleon.dto.dogma;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReligionDTO {
    private Long id;
    private String name;
    private List<String> deities;
    private String description;
}