package com.testament.veltahleon.repositories.dao.ifc.calendar;

import com.testament.veltahleon.model.entities.calendar.Year;

import java.util.Collection;

public interface YearDAO {

    Collection<Year> getYears();
    Year getYearByID(Long id);
    Boolean deleteYearByID(Long id);
    Year saveYear(Year year);
    Year updateYear(Year year);
}
