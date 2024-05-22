package com.testament.veltahleon.mappers.calendar;

import com.testament.veltahleon.dto.calendar.CalendarSystemDTO;
import com.testament.veltahleon.facades.calendar.CalendarSystemFacade;
import com.testament.veltahleon.model.entities.calendar.CalendarSystem;
import com.testament.veltahleon.model.entities.calendar.Day;
import com.testament.veltahleon.model.entities.calendar.Epoch;
import com.testament.veltahleon.model.entities.calendar.Month;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CalendarSystemMapper {

    @Autowired
    private CalendarSystemFacade calendarSystemFacade;

    public CalendarSystemDTO convertToDTO(CalendarSystem calendarSystem) {
        return CalendarSystemDTO.builder()
                .id(calendarSystem.getId())
                .name(calendarSystem.getName())
                .days(calendarSystem.getDays().stream().map(Day::getName).toList())
                .months(calendarSystem.getMonths().stream().map(Month::getName).toList())
                //.epochs(calendarSystem.getEpochs().stream().map(Epoch::getYearNumber).toList())
                .build();
    }

    public CalendarSystem convertToEntity(CalendarSystemDTO calendarSystemDTO) {
        CalendarSystem calendarSystem = new CalendarSystem();
        calendarSystem.setName(calendarSystemDTO.getName());
        calendarSystem.setDays(calendarSystemDTO.getDays().stream().map(d -> calendarSystemFacade.getDay(d)).toList());
        calendarSystem.setMonths(calendarSystemDTO.getMonths().stream().map(m -> calendarSystemFacade.getMonth(m)).toList());
        //calendarSystem.setEpochs(calendarSystemDTO.getEpochs().stream().map(e -> calendarSystemFacade.getEpoch(e)).toList());
        return calendarSystem;
    }
}
