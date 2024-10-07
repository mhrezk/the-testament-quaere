package com.testament.veltahleon.rest.dogma.mythology;

import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.model.entities.dogma.mythology.Deity;
import com.testament.veltahleon.services.ifc.dogma.mythology.DeityService;
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
@RequestMapping("/dogma")
@RequiredArgsConstructor
public class DeityController {

    @Autowired
    private DeityService deityService;

    @GetMapping("/deities")
    public ResponseEntity<CustomResponse> getPaginatedDeities(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Deity> deities = (List<Deity>) deityService.getDeitiesWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", deities))
                .message(deities.size() + " deities retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/deities/all")
    public ResponseEntity<CustomResponse> getAllDeities() {
        List<Deity> deities = (List<Deity>) deityService.getDeities();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", deities))
                .message("All deities retrieved!")
                .build()
        );
    }

    @GetMapping("/deity/name")
    public ResponseEntity<CustomResponse> getDeityByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", deityService.getDeityByName(name)))
                .message("Deity retrieved!")
                .build()
        );
    }

    @GetMapping("/deity/{id}")
    public ResponseEntity<CustomResponse> getDeityByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", deityService.getDeityByID(id)))
                .message("Deity retrieved!")
                .build()
        );
    }

    @GetMapping(value = "/deity/image/{fileName}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public byte[] getDeityImage(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get("src/main/java/com/testament/veltahleon/assets/images/deities/" + fileName));
    }

    @DeleteMapping("/delete/deity/{id}")
    public ResponseEntity<CustomResponse> deleteDeityByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", deityService.deleteDeityByID(id)))
                .message("Deity deleted!")
                .build()
        );
    }

    @PostMapping("/save/deity")
    public ResponseEntity<CustomResponse> saveDeity(@RequestBody @Valid Deity deity) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", deityService.saveDeity(deity)))
                .message("Deity saved!")
                .build()
        );
    }

    @PatchMapping("/update/deity/{id}")
    public ResponseEntity<CustomResponse> updateDeity(@PathVariable("id") Long id, @RequestBody @Valid Deity deity) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", deityService.updateDeity(id, deity)))
                .message("Deity updated!")
                .build()
        );
    }
}
