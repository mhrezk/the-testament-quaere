package com.testament.veltahleon.rest.divination;

import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.model.entities.divination.Constellation;
import com.testament.veltahleon.services.ifc.divination.ConstellationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/divination")
@RequiredArgsConstructor
public class ConstellationController {

    @Autowired
    private ConstellationService constellationService;

    @GetMapping("/constellations")
    public ResponseEntity<CustomResponse> getPaginatedConstellations(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Constellation> constellations = (List<Constellation>) constellationService.getConstellationsWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", constellations))
                .message(constellations.size() + " constellations retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/constellations/all")
    public ResponseEntity<CustomResponse> getAllConstellations() {
        List<Constellation> constellations = (List<Constellation>) constellationService.getConstellations();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", constellations))
                .message("All constellations retrieved!")
                .build()
        );
    }

    @GetMapping("/constellation/{id}")
    public ResponseEntity<CustomResponse> getConstellationByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", constellationService.getConstellationByID(id)))
                .message("Constellation retrieved!")
                .build()
        );
    }

    @GetMapping(value = "/constellation/image/{fileName}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public byte[] getConstellationImage(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get("src/main/java/com/testament/veltahleon/assets/images/constellations/" + fileName));
    }

    @DeleteMapping("/delete/constellation/{id}")
    public ResponseEntity<CustomResponse> deleteConstellationByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", constellationService.deleteConstellationByID(id)))
                .message("Constellation deleted!")
                .build()
        );
    }

    @PostMapping("/save/constellation")
    public ResponseEntity<CustomResponse> saveConstellation(@RequestBody @Valid Constellation constellation) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", constellationService.saveConstellation(constellation)))
                .message("Constellation saved!")
                .build()
        );
    }

    @PatchMapping("/update/constellation/{id}")
    public ResponseEntity<CustomResponse> updateConstellation(@PathVariable("id") Long id, @RequestBody Constellation constellation) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", constellationService.updateConstellation(id, constellation)))
                .message("Constellation updated!")
                .build()
        );
    }
}
