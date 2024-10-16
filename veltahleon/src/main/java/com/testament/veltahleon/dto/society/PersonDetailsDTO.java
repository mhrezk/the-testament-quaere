package com.testament.veltahleon.dto.society;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonDetailsDTO {
    private Long id;
    private String firstName;
    private String secondName;
    private String gender;
    private String raceName;
    private String rank;
    private String religion;
    private String epithet;
    private String nation;
    private Integer age;
    private Integer birthDay;
    //private String birthDayName;
    private Integer birthMonth;
    //private String birthMonthName;
    private Integer birthYear;
    private String birthYearAbbreviation;
    private Integer deathDay;
    //private String deathDayName;
    private Integer deathMonth;
    //private String deathMonthName;
    private Integer deathYear;
    private String deathYearAbbreviation;
    //private String calendarSystem;
    private String job;
    private String biography;
    private String imageURL;
    private String lineage;
    private String maritalStatus;
}
