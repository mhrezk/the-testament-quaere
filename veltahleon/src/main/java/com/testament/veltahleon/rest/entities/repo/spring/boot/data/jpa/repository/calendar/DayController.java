package com.testament.veltahleon.rest.entities.repo.spring.boot.data.jpa.repository.calendar;

import com.testament.veltahleon.model.CustomResponse;
import com.testament.veltahleon.model.entities.calendar.Day;
import com.testament.veltahleon.model.entities.history.Language;
import com.testament.veltahleon.model.entities.history.Letter;
import com.testament.veltahleon.services.entities.repo.ifc.calendar.DayService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/calendar")
@RequiredArgsConstructor
public class DayController {

    @Autowired
    private final DayService dayService;

    @GetMapping("/days")
    public ResponseEntity<CustomResponse> getPaginatedDays(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Day> days = (List<Day>) dayService.getDaysWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("paginatedDays", days))
                .message(days.size() + " days retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/days/all")
    public ResponseEntity<CustomResponse> getAllDays() {
        List<Day> days = (List<Day>) dayService.getDays();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("allDays", days))
                .message("All days retrieved!")
                .build()
        );
    }

    @GetMapping("/day/name")
    public ResponseEntity<CustomResponse> getDayByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedDayByName", dayService.getDayByName(name)))
                .message("Day retrieved!")
                .build()
        );
    }

    @GetMapping("/day/{id}")
    public ResponseEntity<CustomResponse> getDayByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedDayByID", dayService.getDayByID(id)))
                .message("Day retrieved!")
                .build()
        );
    }

    @GetMapping("/days/language")
    public ResponseEntity<CustomResponse> getDayByLanguage(@RequestParam(value = "languageName") String languageName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedDaysByLanguage", dayService.getDaysByLanguage(languageName)))
                .message("Day retrieved!")
                .build()
        );
    }

    @PostMapping("/save/day")
    public ResponseEntity<CustomResponse> saveDay(@RequestBody @Valid Day day, BindingResult result) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("savedDay", dayService.saveDay(day)))
                .message("Day saved!")
                .reason(result.getAllErrors().stream().toString())
                .build()
        );
    }

    @PostMapping("/save/days")
    public ResponseEntity<CustomResponse> saveDays(@RequestBody Collection<Day> days) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("savedDays", dayService.saveDays(days)))
                .message("Days saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/day/{id}")
    public ResponseEntity<CustomResponse> deleteDayByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("isDayDeleted", dayService.deleteDayByID(id)))
                .message("Day deleted!")
                .build()
        );
    }

    @DeleteMapping("/delete/days")
    public ResponseEntity<CustomResponse> deleteAllDays() {
        Collection<Day> days = dayService.getDays();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("areAllDaysDeleted", dayService.deleteAllDays(days)))
                .message("Days deleted!")
                .build()
        );
    }

    @DeleteMapping("/delete/days/id")
    public ResponseEntity<CustomResponse> deleteAllDaysByIDs() {
        Collection<Day> days = dayService.getDays();
        List<Long> idDays = new ArrayList<>();
        for(Day day : days) {
            idDays.add(day.getId());
        }
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("areAllDaysDeleted", dayService.deleteAllDaysByIDs(idDays)))
                .message("Days deleted!")
                .build()
        );
    }

    @PatchMapping("/update/day/{id}")
    public ResponseEntity<CustomResponse> updateDay(@PathVariable Long id, @RequestBody @Valid Day day, BindingResult result) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("updatedDay", dayService.updateDay(id, day)))
                .message("Day updated!")
                .reason(result.getAllErrors().stream().toString())
                .build()
        );
    }
}
