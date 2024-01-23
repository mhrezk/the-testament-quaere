package com.testament.veltahleon.rest.entities.repo.spring.boot.data.jpa.repository.calendar;

import com.testament.veltahleon.model.CustomResponse;
import com.testament.veltahleon.model.entities.calendar.Day;
import com.testament.veltahleon.model.entities.history.Language;
import com.testament.veltahleon.services.entities.repo.ifc.calendar.DayService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CustomResponse> getAllDays(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Day> days = (List<Day>) dayService.getDaysWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("allDays", days))
                .message("Days retrieved!")
                .build()
        );
    }

    @GetMapping("/day/{name}")
    public ResponseEntity<CustomResponse> getDayByName(@PathVariable String name) {
        String firstCharacter = name.substring(0, 2).toUpperCase();
        String properName = firstCharacter + name.substring(2);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("day", dayService.getDayByName(properName)))
                .message("Day retrieved!")
                .build()
        );
    }

    @GetMapping("/days/{languageName}")
    public ResponseEntity<CustomResponse> getDayByLanguage(@PathVariable String languageName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("days", dayService.getDaysByLanguage(languageName)))
                .message("Day retrieved!")
                .build()
        );
    }

    @PostMapping("/save/day")
    public ResponseEntity<CustomResponse> saveDay(@RequestBody Day day) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("savedDay", dayService.saveDay(day)))
                .message("Day saved!")
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
    public ResponseEntity<CustomResponse> deleteDay(@PathVariable Long id) {
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
    public ResponseEntity<CustomResponse> deleteDays() {
        Collection<Day> days = dayService.getDays();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("areDaysDeleted", dayService.deleteAllDays(days)))
                .message("Days deleted!")
                .build()
        );
    }

    @DeleteMapping("/delete/days/id")
    public ResponseEntity<CustomResponse> deleteDaysByIDs() {
        Collection<Day> days = dayService.getDays();
        List<Long> idDays = new ArrayList<>();
        for(Day day : days) {
            idDays.add(day.getId());
        }
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("areDaysDeleted", dayService.deleteAllDaysByIDs(idDays)))
                .message("Days deleted!")
                .build()
        );
    }

    @PatchMapping("/update/day/{id}")
    public ResponseEntity<CustomResponse> updateDay(@PathVariable Long id, @RequestBody Day day) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("updatedDay", dayService.updateDay(id, day)))
                .message("Day updated!")
                .build()
        );
    }
}
