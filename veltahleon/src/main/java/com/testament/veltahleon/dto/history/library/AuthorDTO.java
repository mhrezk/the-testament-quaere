package com.testament.veltahleon.dto.history.library;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorDTO {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String penName;
    private Integer birthYear;
    private String birthYearAbbreviation;
    private Integer deathYear;
    private String gender;
    //private List<String> books;
    private String biography;
    private String imageURL;
}
