package com.testament.veltahleon.mappers.calendar;

import com.testament.veltahleon.dto.calendar.CalendarDTO;
import com.testament.veltahleon.facades.calendar.CalendarFacade;
import com.testament.veltahleon.model.entities.calendar.Calendar;
import com.testament.veltahleon.model.entities.calendar.Day;
import com.testament.veltahleon.model.entities.calendar.Epoch;
import com.testament.veltahleon.model.entities.calendar.Month;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CalendarMapper {

    @Autowired
    private CalendarFacade calendarFacade;

    public CalendarDTO convertToDTO(Calendar calendar) {
        return CalendarDTO.builder()
                .id(calendar.getId())
                .name(calendar.getName())
                .days(calendar.getDays().stream().map(Day::getName).toList())
                .months(calendar.getMonths().stream().map(Month::getName).toList())
                .epochs(calendar.getEpochs().stream().map(Epoch::getYearNumber).toList())
                .build();
    }

    public Calendar convertToEntity(CalendarDTO calendarDTO) {
        Calendar calendar = new Calendar();
        calendar.setName(calendarDTO.getName());
        calendar.setDays(calendarDTO.getDays().stream().map(d -> calendarFacade.getDay(d)).toList());
        calendar.setMonths(calendarDTO.getMonths().stream().map(m -> calendarFacade.getMonth(m)).toList());
        calendar.setEpochs(calendarDTO.getEpochs().stream().map(e -> calendarFacade.getEpoch(e)).toList());
        return calendar;
    }
}
