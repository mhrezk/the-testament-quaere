package com.testament.veltahleon.mappers.history;

import com.testament.veltahleon.dto.history.EventDTO;
import com.testament.veltahleon.model.entities.history.Event;
import com.testament.veltahleon.services.ifc.calendar.YearService;
import com.testament.veltahleon.services.ifc.history.EventService;
import com.testament.veltahleon.services.ifc.history.TimelineService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EventMapper {

//    @Autowired
//    private YearService yearService;

    @Autowired
    private TimelineService timelineService;

    public EventDTO convertToDTO(Event event) {
        return EventDTO.builder()
                .id(event.getId())
                .incident(event.getIncident())
                .eventDay(event.getEventDay())
                .eventMonth(event.getEventMonth())
                .eventYear(event.getEventYear())
                .timeline(event.getTimeline().getName())
                .description(event.getDescription())
                .build();
    }

    public Event convertToEntity(EventDTO eventDTO) {
        Event event = new Event();
        event.setId(eventDTO.getId());
        event.setIncident(eventDTO.getIncident());
        event.setEventDay(eventDTO.getEventDay());
        event.setEventMonth(eventDTO.getEventMonth());
        event.setEventYear(eventDTO.getEventYear());
        event.setTimeline(timelineService.getTimelineByName(eventDTO.getTimeline()));
        event.setDescription(eventDTO.getDescription());
        return event;
    }
}
