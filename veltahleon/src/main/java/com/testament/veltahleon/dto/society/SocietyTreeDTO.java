package com.testament.veltahleon.dto.society;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SocietyTreeDTO {
    private Long id;
    private String treeName;
    private List<String> pedigrees;
}
