package com.testament.veltahleon.mappers.history;

import com.testament.veltahleon.dto.history.TimelineDTO;
import com.testament.veltahleon.model.entities.history.Event;
import com.testament.veltahleon.model.entities.history.Timeline;
import com.testament.veltahleon.services.ifc.history.EventService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class TimelineMapper {

    @Autowired
    private EventService eventService;

    public TimelineDTO convertToDTO(Timeline timeline) {
        return TimelineDTO.builder()
                .id(timeline.getId())
                .name(timeline.getName())
                .events(timeline.getEvents().stream().map(Event::getId).toList())
                .build();
    }

    public Timeline convertToEntity(TimelineDTO timelineDTO) {
        Timeline t = new Timeline();
        t.setName(timelineDTO.getName());
        t.setEvents(timelineDTO.getEvents()
                .stream()
                .map(id -> eventService.getEventByID(id))
                .toList());
        return t;
    }
}
