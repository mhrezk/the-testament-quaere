package com.testament.veltahleon.services.dao.ifc.calendar;

import com.testament.veltahleon.model.entities.calendar.Year;

import java.util.Collection;

public interface YearService {

    Collection<Year> getYears();
    Year getYearByID(Long id);
    Boolean deleteYearByID(Long id);
    Year saveYear(Year year);
    Year updateYear(Year year);
}
