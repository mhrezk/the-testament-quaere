package com.testament.veltahleon.facades.politics;

import com.testament.veltahleon.model.entities.calendar.Year;
import com.testament.veltahleon.model.entities.places.Nation;
import com.testament.veltahleon.model.entities.society.Person;
import com.testament.veltahleon.services.ifc.calendar.YearService;
import com.testament.veltahleon.services.ifc.places.NationService;
import com.testament.veltahleon.services.ifc.society.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrganizationFacade {

    @Autowired
    private PersonService personService;

    @Autowired
    private YearService yearService;

    @Autowired
    private NationService nationService;

    public Person getFounder(String founderName) {
        return personService.getPersonByName(founderName);
    }

    public Nation getNation(String nationName) {
        return nationService.getNationByName(nationName);
    }

    public Year getYear(int day, int month, int yearNumber) {
        return yearService.getYearByDate(day, month, yearNumber);
    }
}
