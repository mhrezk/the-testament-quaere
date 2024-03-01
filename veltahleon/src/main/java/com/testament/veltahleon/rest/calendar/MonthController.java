package com.testament.veltahleon.rest.calendar;

import com.testament.veltahleon.model.entities.calendar.Month;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.calendar.MonthService;
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
public class MonthController {

    @Autowired
    private MonthService monthService;

    @GetMapping("/months")
    public ResponseEntity<CustomResponse> getPaginatedMonths(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Month> months = (List<Month>) monthService.getMonthsWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", months))
                .message(months.size() + " Months retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/months/all")
    public ResponseEntity<CustomResponse> getAllMonths() {
        List<Month> months = (List<Month>) monthService.getMonths();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", months))
                .message("All Months retrieved!")
                .build()
        );
    }

    @GetMapping("/month/{id}")
    public ResponseEntity<CustomResponse> getMonthByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", monthService.getMonthByID(id)))
                .message("Month retrieved!")
                .build()
        );
    }

    @GetMapping("/month/name")
    public ResponseEntity<CustomResponse> getMonthByMonthName(@RequestParam(value = "monthName") String monthName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", monthService.getMonthByName(monthName)))
                .message("Month retrieved!")
                .build()
        );
    }

    @PostMapping("/save/month")
    public ResponseEntity<CustomResponse> saveMonth(@RequestBody @Valid Month month) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", monthService.saveMonth(month)))
                .message("Month saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/month/{id}")
    public ResponseEntity<CustomResponse> deleteMonthByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", monthService.deleteMonthByID(id)))
                .message("Month deleted!")
                .build()
        );
    }

    @PatchMapping("/update/month/{id}")
    public ResponseEntity<CustomResponse> updateMonth(@PathVariable Long id, @RequestBody Month month) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", monthService.updateMonth(id, month)))
                .message("Month updated!")
                .build()
        );
    }
}
