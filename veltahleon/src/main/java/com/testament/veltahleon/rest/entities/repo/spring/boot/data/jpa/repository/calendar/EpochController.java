package com.testament.veltahleon.rest.entities.repo.spring.boot.data.jpa.repository.calendar;

import com.testament.veltahleon.model.entities.calendar.Epoch;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.entities.repo.ifc.calendar.EpochService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
                .data(Map.of("allEpochs", epochs))
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
                .data(Map.of("queriedEpochByID", epochService.getEpochByID(id)))
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
                .data(Map.of("savedEpoch", epochService.saveEpoch(epoch)))
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
                .data(Map.of("isEpochDeleted", epochService.deleteEpochByID(id)))
                .message("Epoch deleted!")
                .build()
        );
    }

    @PatchMapping("/update/epoch/{id}")
    public ResponseEntity<CustomResponse> updateEpoch(@PathVariable Long id, @RequestBody @Valid Epoch epoch) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("updatedEpoch", epochService.updateEpoch(id, epoch)))
                .message("Epoch updated!")
                .build()
        );
    }
}
