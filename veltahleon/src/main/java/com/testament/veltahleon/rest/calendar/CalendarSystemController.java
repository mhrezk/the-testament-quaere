package com.testament.veltahleon.rest.calendar;

import com.testament.veltahleon.model.entities.calendar.CalendarSystem;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.calendar.CalendarSystemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/calendar")
@RequiredArgsConstructor
public class CalendarSystemController {

    @Autowired
    private final CalendarSystemService calendarSystemService;

    @GetMapping("/calendarSystems")
    public ResponseEntity<CustomResponse> getPaginatedCalendars(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<CalendarSystem> calendarSystems = (List<CalendarSystem>) calendarSystemService.getCalendarWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", calendarSystems))
                .message(calendarSystems.size() + " calendars retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/calendarSystems/all")
    public ResponseEntity<CustomResponse> getAllCalendars() {
        List<CalendarSystem> calendarSystems = (List<CalendarSystem>) calendarSystemService.getCalendars();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", calendarSystems))
                .message("All calendars retrieved!")
                .build()
        );
    }

    @GetMapping("/calendarSystem/name")
    public ResponseEntity<CustomResponse> getCalendarByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", calendarSystemService.getCalendarByName(name)))
                .message("Calendar retrieved!")
                .build()
        );
    }

    @GetMapping("/calendarSystem/{id}")
    public ResponseEntity<CustomResponse> getCalendarByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", calendarSystemService.getCalendarByID(id)))
                .message("Calendar retrieved!")
                .build()
        );
    }

    @PostMapping("/save/calendarSystem")
    public ResponseEntity<CustomResponse> saveCalendar(@RequestBody @Valid CalendarSystem calendarSystem) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", calendarSystemService.saveCalendar(calendarSystem)))
                .message("Calendar saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/calendarSystem/{id}")
    public ResponseEntity<CustomResponse> deleteCalendarByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", calendarSystemService.deleteCalendarByID(id)))
                .message("Calendar deleted!")
                .build()
        );
    }

    @PatchMapping("/update/calendarSystem/{id}")
    public ResponseEntity<CustomResponse> updateCalendar(@PathVariable Long id, @RequestBody CalendarSystem calendarSystem) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", calendarSystemService.updateCalendar(id, calendarSystem)))
                .message("Calendar updated!")
                .build()
        );
    }

    @PutMapping("/modify/calendarSystem/{id}")
    public ResponseEntity<CustomResponse> modifyCalendar(@PathVariable Long id, @RequestBody CalendarSystem calendarSystem) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", calendarSystemService.modifyCalendar(id, calendarSystem)))
                .message("Calendar updated!")
                .build()
        );
    }
}
