package com.testament.veltahleon.mappers.history;

import com.testament.veltahleon.dto.history.EventDTO;
import com.testament.veltahleon.model.entities.history.Event;
import com.testament.veltahleon.services.ifc.calendar.YearService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EventMapper {

    @Autowired
    private YearService yearService;

    public EventDTO convertToDTO(Event event) {
        return EventDTO.builder()
                .id(event.getId())
                .incident(event.getIncident())
                .beginningDay(String.valueOf(event.getBeginningYear().getDay().getDayNumber()))
                .beginningMonth(String.valueOf(event.getBeginningYear().getMonth().getMonthNumber()))
                .beginningYear(String.valueOf(event.getBeginningYear().getEpoch().getYearNumber()))
                .description(event.getDescription())
                .build();
    }

    public Event convertToEntity(EventDTO eventDTO) {
        String[] beginningEventDate = eventDTO.getBeginningEventYear().split("/", 3);
        String[] endingEventDate = eventDTO.getEndingEventYear().split("/", 3);
        Event e = new Event();
        e.setIncident(eventDTO.getIncident());
        e.setBeginningYear(yearService.getYearByDate(Integer.parseInt(beginningEventDate[0]), Integer.parseInt(beginningEventDate[1]), Integer.parseInt(beginningEventDate[2])));
        e.setEndingYear(yearService.getYearByDate(Integer.parseInt(endingEventDate[0]), Integer.parseInt(endingEventDate[1]), Integer.parseInt(endingEventDate[2])));
        e.setDescription(eventDTO.getDescription());
        return e;
    }
}
