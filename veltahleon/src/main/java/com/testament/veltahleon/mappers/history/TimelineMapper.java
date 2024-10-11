//package com.testament.veltahleon.mappers.history;
//
//import com.testament.veltahleon.dto.history.TimelineDTO;
//import com.testament.veltahleon.model.entities.history.Event;
//import com.testament.veltahleon.model.entities.history.Timeline;
//import com.testament.veltahleon.services.ifc.history.EventService;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//@AllArgsConstructor
//public class TimelineMapper {
//
//    @Autowired
//    private EventService eventService;
//
//    public TimelineDTO convertToDTO(Timeline timeline) {
//        return TimelineDTO.builder()
//                .id(timeline.getId())
//                .name(timeline.getName())
//                .beginningYear(timeline.getBeginningYear())
//                .beginningYearAbbreviation(timeline.getBeginningYearAbbreviation())
//                .endingYear(timeline.getEndingYear())
//                .endingYearAbbreviation(timeline.getEndingYearAbbreviation())
//                .events(timeline.getEvents().stream().map(Event::getId).toList())
//                .build();
//    }
//
//    public Timeline convertToEntity(TimelineDTO timelineDTO) {
//        List<Long> idList = timelineDTO.getEvents();
//        List<Event> eventList = idList.stream().map(i -> eventService.getEventByID(i)).toList();
//        Timeline timeline = new Timeline();
//        timeline.setId(timelineDTO.getId());
//        timeline.setName(timelineDTO.getName());
//        timeline.setBeginningYear(timelineDTO.getBeginningYear());
//        timeline.setBeginningYearAbbreviation(timelineDTO.getBeginningYearAbbreviation());
//        timeline.setEndingYear(timelineDTO.getEndingYear());
//        timeline.setEndingYearAbbreviation(timelineDTO.getEndingYearAbbreviation());
//        timeline.setEvents(eventList);
//        return timeline;
//    }
//}
