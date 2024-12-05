package com.testament.veltahleon.dto.history;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VocabularyDTO {
    private Long id;
    private String word;
    private String definition;
    private String pronunciation;
    private String language;
}
