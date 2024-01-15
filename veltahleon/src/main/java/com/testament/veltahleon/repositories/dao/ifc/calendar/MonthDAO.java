package com.testament.veltahleon.repositories.dao.ifc.calendar;

import com.testament.veltahleon.model.entities.calendar.Month;

import java.util.Collection;

public interface MonthDAO {

    Collection<Month> getMonths();
    Month getMonthByID(Long id);
    Boolean deleteMonthByID(Long id);
    Month saveMonth(Month month);
    Month updateMonth(Month month);
}
