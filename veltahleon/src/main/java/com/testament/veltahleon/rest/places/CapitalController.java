package com.testament.veltahleon.rest.places;

import com.testament.veltahleon.model.entities.places.Capital;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.places.CapitalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/places")
@RequiredArgsConstructor
public class CapitalController {

    @Autowired
    private CapitalService capitalService;

    public final String IMAGE_PATH = "src/main/resources/assets/images/flags/";

    @GetMapping("/capitals")
    public ResponseEntity<CustomResponse> getPaginatedCapitals(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Capital> capitals = (List<Capital>) capitalService.getCapitalsWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", capitals))
                .message(capitals.size() + " capitals retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/capitals/all")
    public ResponseEntity<CustomResponse> getAllCapitals() {
        List<Capital> capitals = (List<Capital>) capitalService.getCapitals();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", capitals))
                .message("All capitals retrieved!")
                .build()
        );
    }

    @GetMapping("/capital/{id}")
    public ResponseEntity<CustomResponse> getCapitalByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", capitalService.getCapitalByID(id)))
                .message("Capital retrieved!")
                .build()
        );
    }

    @GetMapping("/capital/name")
    public ResponseEntity<CustomResponse> getCapitalByCapitalName(@RequestParam(value = "capitalName") String capitalName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", capitalService.getCapitalByName(capitalName)))
                .message("Capital retrieved!")
                .build()
        );
    }

    @GetMapping(path = "/capitals/images/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getFlagImage(@PathVariable("imageName") String imageName) throws IOException {
        return Files.readAllBytes(Path.of(IMAGE_PATH + imageName));
    }

    @PostMapping("/save/capital")
    public ResponseEntity<CustomResponse> saveCapital(@RequestBody @Valid Capital capital) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", capitalService.saveCapital(capital)))
                .message("Capital saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/capital/{id}")
    public ResponseEntity<CustomResponse> deleteCapitalByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", capitalService.deleteCapitalByID(id)))
                .message("Capital deleted!")
                .build()
        );
    }

    @PatchMapping("/update/capital/{id}")
    public ResponseEntity<CustomResponse> updateCapital(@PathVariable Long id, @RequestBody Capital capital) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", capitalService.updateCapital(id, capital)))
                .message("Capital updated!")
                .build()
        );
    }
}
