package com.testament.veltahleon.rest.calendar;

import com.testament.veltahleon.model.entities.calendar.Calendar;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.calendar.CalendarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/calendar")
@RequiredArgsConstructor
public class CalendarController {

    @Autowired
    private final CalendarService calendarService;

    @GetMapping("/calendars")
    public ResponseEntity<CustomResponse> getPaginatedCalendars(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Calendar> calendars = (List<Calendar>) calendarService.getCalendarWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", calendars))
                .message(calendars.size() + " calendars retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/calendars/all")
    public ResponseEntity<CustomResponse> getAllCalendars() {
        List<Calendar> calendars = (List<Calendar>) calendarService.getCalendars();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", calendars))
                .message("All calendars retrieved!")
                .build()
        );
    }

    @GetMapping("/calendar/name")
    public ResponseEntity<CustomResponse> getCalendarByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", calendarService.getCalendarByName(name)))
                .message("Calendar retrieved!")
                .build()
        );
    }

    @GetMapping("/calendar/{id}")
    public ResponseEntity<CustomResponse> getCalendarByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", calendarService.getCalendarByID(id)))
                .message("Calendar retrieved!")
                .build()
        );
    }

    @PostMapping("/save/calendar")
    public ResponseEntity<CustomResponse> saveCalendar(@RequestBody @Valid Calendar calendar) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", calendarService.saveCalendar(calendar)))
                .message("Calendar saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/calendar/{id}")
    public ResponseEntity<CustomResponse> deleteCalendarByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", calendarService.deleteCalendarByID(id)))
                .message("Calendar deleted!")
                .build()
        );
    }

    @PatchMapping("/update/calendar/{id}")
    public ResponseEntity<CustomResponse> updateCalendar(@PathVariable Long id, @RequestBody Calendar calendar) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", calendarService.updateCalendar(id, calendar)))
                .message("Calendar updated!")
                .build()
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomResponse> modifyCalendar(@PathVariable Long id, @RequestBody Calendar calendar) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", calendarService.modifyCalendar(id, calendar)))
                .message("Calendar updated!")
                .build()
        );
    }
}
