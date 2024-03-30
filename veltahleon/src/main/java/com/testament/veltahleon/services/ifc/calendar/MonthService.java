package com.testament.veltahleon.services.ifc.calendar;

import com.testament.veltahleon.model.entities.calendar.Month;

import java.util.Collection;

public interface MonthService {

    Collection<Month> getMonthsWithPagination(int pageNumber, int numberOfRecords);
    Collection<Month> getMonths();
    //Collection<Month> getMonthsByLanguageName(String name);
    Month getMonthByID(Long id);
    Month getMonthByName(String name);
    Boolean deleteMonthByID(Long id);
    Month saveMonth(Month month);
    Month updateMonth(Long id, Month month);
}
