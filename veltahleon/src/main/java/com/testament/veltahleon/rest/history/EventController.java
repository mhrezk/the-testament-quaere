package com.testament.veltahleon.rest.history;

import com.testament.veltahleon.dto.history.EventDTO;
import com.testament.veltahleon.mappers.history.EventMapper;
import com.testament.veltahleon.model.entities.history.Event;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.history.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventMapper eventMapper;

    @GetMapping("/events")
    public ResponseEntity<CustomResponse> getPaginatedEvents(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Event> events = (List<Event>) eventService.getPaginatedEvents((pageNumber - 1), pageSize);
        List<EventDTO> eventsDTO = events.stream().map(e -> eventMapper.convertToDTO(e)).toList();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", eventsDTO))
                .message(events.size() + " events retrieved from page: " + pageNumber)
                .build()
        );
    }

    @GetMapping("/events/all")
    public ResponseEntity<CustomResponse> getAllEvents() {
        List<Event> events = (List<Event>) eventService.getEvents();
        List<EventDTO> eventsDTO = events.stream().map(e -> eventMapper.convertToDTO(e)).toList();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", eventsDTO))
                .message("All events retrieved!")
                .build()
        );
    }

    @GetMapping("/events/{timeline}")
    public ResponseEntity<CustomResponse> getEventsByTimelineName(@PathVariable(value = "timeline") String timeline) {
        List<Event> events = (List<Event>) eventService.getEventsByTimeline(timeline);
        List<EventDTO> eventsDTO = events.stream().map(e -> eventMapper.convertToDTO(e)).toList();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", eventsDTO))
                .message(eventsDTO.size() + " events for " + timeline + " timeline retrieved!")
                .build()
        );
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<CustomResponse> getEventByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", eventMapper.convertToDTO(eventService.getEventByID(id))))
                .message("Event retrieved!")
                .build()
        );
    }

    @GetMapping("/event/eventYear")
    public ResponseEntity<CustomResponse> getEventsByEventYear(@RequestParam(value = "year") int year) {
        List<EventDTO> eventsDTO = eventService.getEventsByEventYear(year).stream().map(e -> eventMapper.convertToDTO(e)).toList();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", eventsDTO))
                .message("Event retrieved!")
                .build()
        );
    }

    @GetMapping("/event/{eventDay}/{eventMonth}/{eventYear}")
    public ResponseEntity<CustomResponse> getEventByEventDayAndEventMonthAndEventYear(@PathVariable(value = "eventDay") int eventDay, @PathVariable(value = "eventMonth") int eventMonth, @PathVariable(value = "eventYear") int eventYear) {
        EventDTO eventDTO = eventMapper.convertToDTO(eventService.getEventByEventDayAndEventMonthAndEventYear(eventDay, eventMonth, eventYear));
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", eventDTO))
                .message("Event retrieved!")
                .build()
        );
    }

    @PostMapping("/save/event/{timeline}")
    public ResponseEntity<CustomResponse> saveEvent(@RequestBody @Valid EventDTO eventDTO, @PathVariable(value = "timeline") String timeline) {
        Event event = eventMapper.convertToEntity(eventDTO);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", eventMapper.convertToDTO(eventService.saveEvent(event, timeline))))
                .message("Event saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/event/{id}")
    public ResponseEntity<CustomResponse> deleteEventByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", eventService.deleteEventByID(id)))
                .message("Event deleted!")
                .build()
        );
    }

    @PatchMapping("/update/event/{id}")
    public ResponseEntity<CustomResponse> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", eventService.updateEvent(id, event)))
                .message("Event updated!")
                .build()
        );
    }

    @PutMapping("/modify/event/{id}")
    public ResponseEntity<CustomResponse> modifyEvent(@PathVariable Long id, @RequestBody EventDTO eventDTO) {
        Event event = eventMapper.convertToEntity(eventDTO);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", eventMapper.convertToDTO(eventService.modifyEvent(id, event))))
                .message("Event updated!")
                .build()
        );
    }
}
