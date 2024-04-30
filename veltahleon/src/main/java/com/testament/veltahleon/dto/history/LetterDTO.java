package com.testament.veltahleon.dto.history;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LetterDTO {
    private Long id;
    private String name;
    private String ipa;
    private String language;
    private String scriptURL;
}
