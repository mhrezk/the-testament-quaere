package com.testament.veltahleon.dto.society;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class PersonDTO {
    String name;
    String gender;
    String race;
    String familyName;
    String religion;
    String title;
    String nation;
    Integer birthYear;
    Integer deathYear;
    String job;
    String biography;
    String imageURL;
}
