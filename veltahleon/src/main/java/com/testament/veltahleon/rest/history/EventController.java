package com.testament.veltahleon.rest.history;

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

    @GetMapping("/events")
    public ResponseEntity<CustomResponse> getPaginatedEvents(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Event> events = (List<Event>) eventService.getEventsWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", events))
                .message(events.size() + " events retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/events/all")
    public ResponseEntity<CustomResponse> getAllEvents() {
        List<Event> events = (List<Event>) eventService.getEvents();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", events))
                .message("All events retrieved!")
                .build()
        );
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<CustomResponse> getEventByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", eventService.getEventByID(id)))
                .message("Event retrieved!")
                .build()
        );
    }

    @GetMapping("/event/year")
    public ResponseEntity<CustomResponse> getEventsByEventName(@RequestParam(value = "yearNumber") int yearNumber) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", eventService.getEventsByYearNumber(yearNumber)))
                .message("Event retrieved!")
                .build()
        );
    }

    @PostMapping("/save/event")
    public ResponseEntity<CustomResponse> saveEvent(@RequestBody @Valid Event event) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", eventService.saveEvent(event)))
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
    public ResponseEntity<CustomResponse> updateEvent(@PathVariable Long id, @RequestBody Event Event) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", eventService.updateEvent(id, Event)))
                .message("Event updated!")
                .build()
        );
    }
}
