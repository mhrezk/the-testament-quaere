package com.testament.veltahleon.dto.society;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonDTO {
    private Long id;
    private String name;
    private String gender;
    private String raceName;
    private String family;
    private String religion;
    private String title;
    private String nation;
    private String birthDay;
    private String birthMonth;
    private String birthYear;
    private String deathDay;
    private String deathMonth;
    private String deathYear;
    private String job;
    private String biography;
    private String imageURL;

    public String getBirthDate() {
        return birthDay + "/" + birthMonth + "/" + birthYear;
    }

    public String getDeathDate() {
        return deathDay + "/" + deathMonth + "/" + deathYear;
    }
}
