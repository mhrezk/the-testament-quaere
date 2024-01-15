package com.testament.veltahleon.services.dao.ifc.calendar;

import com.testament.veltahleon.model.entities.calendar.Month;

import java.util.Collection;

public interface MonthService {

    Collection<Month> getMonths();
    Month getMonthByID(Long id);
    Boolean deleteMonthByID(Long id);
    Month saveMonth(Month month);
    Month updateMonth(Month month);
}
