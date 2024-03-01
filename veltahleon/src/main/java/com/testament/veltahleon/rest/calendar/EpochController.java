package com.testament.veltahleon.rest.calendar;

import com.testament.veltahleon.model.entities.calendar.Epoch;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.calendar.EpochService;
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
public class EpochController {

    @Autowired
    private EpochService epochService;

    @GetMapping("/epochs/all")
    public ResponseEntity<CustomResponse> getAllEpochs() {
        List<Epoch> epochs = (List<Epoch>) epochService.getEpochs();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", epochs))
                .message("All epochs retrieved!")
                .build()
        );
    }

    @GetMapping("/epoch/{id}")
    public ResponseEntity<CustomResponse> getEpochByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", epochService.getEpochByID(id)))
                .message("Epoch retrieved!")
                .build()
        );
    }

    @PostMapping("/save/epoch")
    public ResponseEntity<CustomResponse> saveEpoch(@RequestBody @Valid Epoch epoch) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", epochService.saveEpoch(epoch)))
                .message("Epoch saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/epoch/{id}")
    public ResponseEntity<CustomResponse> deleteEpochByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", epochService.deleteEpochByID(id)))
                .message("Epoch deleted!")
                .build()
        );
    }

    @PatchMapping("/update/epoch/{id}")
    public ResponseEntity<CustomResponse> updateEpoch(@PathVariable Long id, @RequestBody Epoch epoch) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", epochService.updateEpoch(id, epoch)))
                .message("Epoch updated!")
                .build()
        );
    }
}
