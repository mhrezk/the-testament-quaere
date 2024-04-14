package com.testament.veltahleon.dto.society;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonDTO {
    Long id;
    String name;
    String gender;
    String raceName;
    String family;
    String religion;
    String title;
    String nation;
    Integer birthYear;
    Integer deathYear;
    String job;
    String biography;
    String imageURL;
}
