package com.testament.veltahleon.services.ifc.calendar;

import com.testament.veltahleon.model.entities.calendar.Year;

import java.util.Collection;

public interface YearService {

    Collection<Year> getYearsWithPagination(int pageNumber, int numberOfRecords);
    Collection<Year> getYears();
    Collection<Year> getYearByName(String name);
    Collection<Year> getYearsByYearNumber(int year);
    Year getYearByID(Long id);
    Year getYearByDate(int day, int month, int year);
    Boolean deleteYearByID(Long id);
    Year saveYear(Year year);
    Year updateYear(Long id, Year year);
}
