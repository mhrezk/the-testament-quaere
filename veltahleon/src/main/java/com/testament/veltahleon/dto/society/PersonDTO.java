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
    String birthDay;
    String birthMonth;
    String birthYear;
    String deathDay;
    String deathMonth;
    String deathYear;
    String job;
    String biography;
    String imageURL;

    public String getBirthDate() {
        return birthDay + "/" + birthMonth + "/" + birthYear;
    }

    public String getDeathDate() {
        return deathDay + "/" + deathMonth + "/" + deathYear;
    }
}
