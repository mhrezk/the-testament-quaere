package com.testament.veltahleon.rest.places;

import com.testament.veltahleon.model.entities.places.Landmark;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.places.LandmarkService;
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
@RequestMapping("/places")
@RequiredArgsConstructor
public class LandmarkController {

    @Autowired
    private LandmarkService landmarkService;

    @GetMapping("/landmarks")
    public ResponseEntity<CustomResponse> getPaginatedLandmarks(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Landmark> landmarks = (List<Landmark>) landmarkService.getLandmarksWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", landmarks))
                .message(landmarks.size() + " landmarks retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/landmarks/all")
    public ResponseEntity<CustomResponse> getAllLandmarks() {
        List<Landmark> landmarks = (List<Landmark>) landmarkService.getLandmarks();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", landmarks))
                .message("All landmarks retrieved!")
                .build()
        );
    }

    @GetMapping("/landmark/{id}")
    public ResponseEntity<CustomResponse> getLandmarkByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", landmarkService.getLandmarkByID(id)))
                .message("Landmark retrieved!")
                .build()
        );
    }

    @GetMapping("/landmark/name")
    public ResponseEntity<CustomResponse> getLandmarksByLandmarkName(@RequestParam(value = "LandmarkName") String landmarkName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", landmarkService.getLandmarkByName(landmarkName)))
                .message("Landmark retrieved!")
                .build()
        );
    }

    @PostMapping("/save/landmark")
    public ResponseEntity<CustomResponse> saveLandmark(@RequestBody @Valid Landmark landmark) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", landmarkService.saveLandmark(landmark)))
                .message("Landmark saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/landmark/{id}")
    public ResponseEntity<CustomResponse> deleteLandmarkByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", landmarkService.deleteLandmarkByID(id)))
                .message("Landmark deleted!")
                .build()
        );
    }

    @PatchMapping("/update/landmark/{id}")
    public ResponseEntity<CustomResponse> updateLandmark(@PathVariable Long id, @RequestBody Landmark landmark) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", landmarkService.updateLandmark(id, landmark)))
                .message("Landmark updated!")
                .build()
        );
    }
}
