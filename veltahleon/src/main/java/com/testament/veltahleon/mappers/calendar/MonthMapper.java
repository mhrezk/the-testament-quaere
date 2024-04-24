package com.testament.veltahleon.mappers.calendar;

import com.testament.veltahleon.dto.calendar.MonthDTO;
import com.testament.veltahleon.model.entities.calendar.Day;
import com.testament.veltahleon.model.entities.calendar.Month;
import com.testament.veltahleon.services.ifc.calendar.DayService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class MonthMapper {

    @Autowired
    private DayService dayService;

    public MonthDTO convertToDTO(Month month) {
        return MonthDTO.builder()
                .id(month.getId())
                .name(month.getName())
                .monthNumber(month.getMonthNumber())
                .numberOfDays(month.getNumberOfDays())
                .description(month.getDescription())
                .build();
    }

    public Month convertToEntity(MonthDTO monthDTO) {
        Month month = new Month();
        month.setMonthNumber(monthDTO.getMonthNumber());
        month.setDescription(monthDTO.getDescription());
        month.setNumberOfDays(monthDTO.getNumberOfDays());
        month.setName(monthDTO.getName());
        return month;
    }
}
