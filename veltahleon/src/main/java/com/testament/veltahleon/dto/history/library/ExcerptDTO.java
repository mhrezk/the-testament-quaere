package com.testament.veltahleon.dto.history.library;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExcerptDTO {
    private Long id;
    private String body;
    private String book;
}
