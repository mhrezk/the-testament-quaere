package com.testament.veltahleon.dto.history.library;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDTO {
    private Long id;
    private String name;
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private Integer releaseDate;
    private String releaseYearAbbreviation;
    private String coverURL;
}
