package com.testament.veltahleon.mappers.calendar;

import com.testament.veltahleon.dto.calendar.YearDTO;
import com.testament.veltahleon.facades.calendar.YearFacade;
import com.testament.veltahleon.model.entities.calendar.Year;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class YearMapper {

    @Autowired
    private YearFacade yearFacade;

    public YearDTO convertToDTO(Year year) {
        return YearDTO.builder()
                .id(year.getId())
                .day(year.getDay().getName())
                .month(year.getMonth().getName())
                .epoch(year.getEpoch().getYearNumber())
                .name(year.getName())
                .build();
    }

    public Year convertToEntity(YearDTO yearDTO) {
        Year year = new Year();
        year.setName(yearDTO.getName());
        year.setDay(yearFacade.getDay(yearDTO.getDay()));
        year.setMonth(yearFacade.getMonth(yearDTO.getMonth()));
        year.setEpoch(yearFacade.getEpoch(yearDTO.getEpoch()));
        return year;
    }
}
