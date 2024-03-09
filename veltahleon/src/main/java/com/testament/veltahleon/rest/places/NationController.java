package com.testament.veltahleon.rest.places;

import com.testament.veltahleon.model.entities.places.Nation;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.places.NationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/places")
@RequiredArgsConstructor
public class NationController {

    @Autowired
    private NationService nationService;

    @GetMapping("/nations")
    public ResponseEntity<CustomResponse> getPaginatedNations(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Nation> nations = (List<Nation>) nationService.getNationsWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", nations))
                .message(nations.size() + " nations retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/Nations/all")
    public ResponseEntity<CustomResponse> getAllNations() {
        List<Nation> nations = (List<Nation>) nationService.getNations();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", nations))
                .message("All nations retrieved!")
                .build()
        );
    }

    @GetMapping("/nation/{id}")
    public ResponseEntity<CustomResponse> getNationByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", nationService.getNationByID(id)))
                .message("Nation retrieved!")
                .build()
        );
    }

    @GetMapping("/nation/name")
    public ResponseEntity<CustomResponse> getNationByNationName(@RequestParam(value = "nationName") String nationName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", nationService.getNationByName(nationName)))
                .message("Nation retrieved!")
                .build()
        );
    }

    @GetMapping(value = "/nation/image/{fileName}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public byte[] getNationFlag(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get("src/main/java/com/testament/veltahleon/assets/images/flags/" + fileName));
    }

    @PostMapping("/save/nation")
    public ResponseEntity<CustomResponse> saveNation(@RequestBody @Valid Nation nation) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", nationService.saveNation(nation)))
                .message("Nation saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/nation/{id}")
    public ResponseEntity<CustomResponse> deleteNationByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", nationService.deleteNationByID(id)))
                .message("Nation deleted!")
                .build()
        );
    }

    @PatchMapping("/update/nation/{id}")
    public ResponseEntity<CustomResponse> updateNation(@PathVariable Long id, @RequestBody Nation nation) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", nationService.updateNation(id, nation)))
                .message("Nation updated!")
                .build()
        );
    }
}
