package com.testament.veltahleon.rest.history;

import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.model.entities.history.Measurement;
import com.testament.veltahleon.services.ifc.history.MeasurementService;
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
@RequestMapping("/history")
@RequiredArgsConstructor
public class MeasurementController {

    @Autowired
    private MeasurementService measurementService;

    @GetMapping("/measurements")
    public ResponseEntity<CustomResponse> getPaginatedMeasurements(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Measurement> measurements = (List<Measurement>) measurementService.getMeasurementsWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", measurements))
                .message(measurements.size() + " measurements retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/measurements/all")
    public ResponseEntity<CustomResponse> getAllMeasurements() {
        List<Measurement> measurements = (List<Measurement>) measurementService.getMeasurements();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", measurements))
                .message("All measurements retrieved!")
                .build()
        );
    }

    @GetMapping("/measurement/{id}")
    public ResponseEntity<CustomResponse> getMeasurementByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", measurementService.getMeasurementByID(id)))
                .message("Measurement retrieved!")
                .build()
        );
    }

    @DeleteMapping("/delete/measurement/{id}")
    public ResponseEntity<CustomResponse> deleteMeasurementByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", measurementService.deleteMeasurementByID(id)))
                .message("Measurement deleted!")
                .build()
        );
    }

    @PostMapping("/save/measurement")
    public ResponseEntity<CustomResponse> saveMeasurement(@RequestBody @Valid Measurement measurement) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", measurementService.saveMeasurement(measurement)))
                .message("Measurement saved!")
                .build()
        );
    }

    @PatchMapping("/update/measurement/{id}")
    public ResponseEntity<CustomResponse> updateMeasurement(@PathVariable("id") Long id, @RequestBody @Valid Measurement measurement) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", measurementService.updateMeasurement(id, measurement)))
                .message("Measurement updated!")
                .build()
        );
    }
}
