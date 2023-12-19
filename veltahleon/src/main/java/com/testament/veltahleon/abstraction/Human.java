package com.testament.veltahleon.abstraction;

import com.testament.veltahleon.entities.calendar.Year;
import com.testament.veltahleon.entities.history.Race;
import com.testament.veltahleon.entities.places.Nation;
import com.testament.veltahleon.entities.society.Family;
import com.testament.veltahleon.entities.society.Job;
import com.testament.veltahleon.entities.society.Religion;
import com.testament.veltahleon.entities.society.Title;
import com.testament.veltahleon.entities.society.enumeration.Gender;
import com.testament.veltahleon.entities.society.enumeration.LifeStatus;
import com.testament.veltahleon.entities.society.enumeration.MaritalStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public abstract class Human {

    @Getter
    @Setter
    private StringBuilder personalName;

    @Getter
    @Setter
    private Race personalRace;

    @Getter
    @Setter
    private Gender personalGender;

    @Getter
    @Setter
    private Religion personalReligion;

    @Getter
    @Setter
    private MaritalStatus maritalStatus;

    @Getter
    @Setter
    private Family personalFamily;

    @Getter
    @Setter
    private List<Job> personalJobs;

    @Getter
    @Setter
    private List<Title> titles;

    @Getter
    @Setter
    private LifeStatus lifestatus;

    @Getter
    @Setter
    private Year birthYear;

    @Getter
    @Setter
    private Year deathYear;

    @Getter
    @Setter
    private Nation personalNation;

    @Getter
    @Setter
    private StringBuilder personalBiography;

    public Human() {}

}
