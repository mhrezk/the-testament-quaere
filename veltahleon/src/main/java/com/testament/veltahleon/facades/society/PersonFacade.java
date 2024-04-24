package com.testament.veltahleon.facades.society;

import com.testament.veltahleon.model.entities.calendar.Year;
import com.testament.veltahleon.model.entities.dogma.Religion;
import com.testament.veltahleon.model.entities.history.Race;
import com.testament.veltahleon.model.entities.places.Nation;
import com.testament.veltahleon.model.entities.society.Family;
import com.testament.veltahleon.model.entities.society.Job;
import com.testament.veltahleon.model.entities.society.Title;
import com.testament.veltahleon.services.ifc.calendar.YearService;
import com.testament.veltahleon.services.ifc.history.RaceService;
import com.testament.veltahleon.services.ifc.places.NationService;
import com.testament.veltahleon.services.ifc.religion.ReligionService;
import com.testament.veltahleon.services.ifc.society.FamilyService;
import com.testament.veltahleon.services.ifc.society.JobService;
import com.testament.veltahleon.services.ifc.society.TitleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class PersonFacade {

    @Autowired
    public RaceService raceService;

    @Autowired
    public FamilyService familyService;

    @Autowired
    public ReligionService religionService;

    @Autowired
    public NationService nationService;

    @Autowired
    public TitleService titleService;

    @Autowired
    public JobService jobService;

    @Autowired
    public YearService yearService;

    public Race getRace(String raceName) {
        return raceService.getRaceByName(raceName);
    }

    public Family getFamily(String familyName) {
        return familyService.getFamilyByFamilyName(familyName);
    }

    public Religion getReligion(String religionName) {
        return religionService.getReligionByName(religionName);
    }

    public Job getJob(String jobName) {
        return jobService.getJobByName(jobName);
    }

    public Nation getNation(String nationName) {
        return nationService.getNationByName(nationName);
    }

    public Title getTitle(String titleName) {
        return titleService.getTitleByName(titleName);
    }

    public Year getYear(int day, int month, int yearNumber) {
        return yearService.getYearByDate(day, month, yearNumber);
    }
}
