package com.testament.veltahleon.rest.calendar;

import com.testament.veltahleon.model.entities.calendar.Year;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.calendar.YearService;
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
public class YearController {

    @Autowired
    private YearService yearService;

    @GetMapping("/years")
    public ResponseEntity<CustomResponse> getPaginatedYears(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Year> years = (List<Year>) yearService.getYearsWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", years))
                .message(years.size() + " years retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/years/all")
    public ResponseEntity<CustomResponse> getAllYears() {
        List<Year> years = (List<Year>) yearService.getYears();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", years))
                .message("All years retrieved!")
                .build()
        );
    }

    @GetMapping("/year/{id}")
    public ResponseEntity<CustomResponse> getYearByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", yearService.getYearByID(id)))
                .message("Year retrieved!")
                .build()
        );
    }

    @GetMapping("/year/name")
    public ResponseEntity<CustomResponse> getYearByYearName(@RequestParam(value = "yearName") String yearName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", yearService.getYearByName(yearName)))
                .message("Year retrieved!")
                .build()
        );
    }

    @GetMapping("/year/date")
    public ResponseEntity<CustomResponse> getYearByDate(@RequestParam(value = "dayNumber") int day, @RequestParam(value = "monthNumber") int month, @RequestParam(value = "yearNumber") int year) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", yearService.getYearByDate(day, month, year)))
                .message("Year retrieved!")
                .build()
        );
    }

    @PostMapping("/save/year")
    public ResponseEntity<CustomResponse> saveYear(@RequestBody @Valid Year year) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", yearService.saveYear(year)))
                .message("Year saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/year/{id}")
    public ResponseEntity<CustomResponse> deleteYearByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", yearService.deleteYearByID(id)))
                .message("Year deleted!")
                .build()
        );
    }

    @PatchMapping("/update/year/{id}")
    public ResponseEntity<CustomResponse> updateYear(@PathVariable Long id, @RequestBody Year year) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", yearService.updateYear(id, year)))
                .message("Year updated!")
                .build()
        );
    }
}
