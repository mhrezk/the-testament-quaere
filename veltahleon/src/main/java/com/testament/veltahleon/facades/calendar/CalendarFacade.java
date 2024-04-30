package com.testament.veltahleon.facades.calendar;

import com.testament.veltahleon.model.entities.calendar.Day;
import com.testament.veltahleon.model.entities.calendar.Epoch;
import com.testament.veltahleon.model.entities.calendar.Month;
import com.testament.veltahleon.services.ifc.calendar.DayService;
import com.testament.veltahleon.services.ifc.calendar.EpochService;
import com.testament.veltahleon.services.ifc.calendar.MonthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CalendarFacade {

    @Autowired
    private DayService dayService;

    @Autowired
    private MonthService monthService;

    @Autowired
    private EpochService epochService;

    public Day getDay(String dayName) {
        return dayService.getDayByName(dayName);
    }

    public Month getMonth(String monthName) {
        return monthService.getMonthByName(monthName);
    }

    public Epoch getEpoch(Integer yearNumber) {
        return epochService.getEpochByYearNumber(yearNumber);
    }
}
