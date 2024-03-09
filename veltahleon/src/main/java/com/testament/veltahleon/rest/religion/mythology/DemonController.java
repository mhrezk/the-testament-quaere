package com.testament.veltahleon.rest.religion.mythology;

import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.model.entities.religion.mythology.Demon;
import com.testament.veltahleon.services.ifc.religion.mythology.DemonService;
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
@RequestMapping("/religion")
@RequiredArgsConstructor
public class DemonController {

    @Autowired
    private DemonService demonService;

    @GetMapping("/demons")
    public ResponseEntity<CustomResponse> getPaginatedDemons(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Demon> demons = (List<Demon>) demonService.getDemonsWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", demons))
                .message(demons.size() + " demons retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/demons/all")
    public ResponseEntity<CustomResponse> getAllDemons() {
        List<Demon> demons = (List<Demon>) demonService.getDemons();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", demons))
                .message("All demons retrieved!")
                .build()
        );
    }

    @GetMapping("/demon/name")
    public ResponseEntity<CustomResponse> getDemonByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", demonService.getDemonByName(name)))
                .message("Demon retrieved!")
                .build()
        );
    }

    @GetMapping("/demon/{id}")
    public ResponseEntity<CustomResponse> getDemonByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", demonService.getDemonByID(id)))
                .message("Demon retrieved!")
                .build()
        );
    }

    @GetMapping(value = "/demon/image/{fileName}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public byte[] getDemonImage(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get("src/main/java/com/testament/veltahleon/assets/images/demons/" + fileName));
    }

    @DeleteMapping("/delete/demon/{id}")
    public ResponseEntity<CustomResponse> deleteDemonByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", demonService.deleteDemonByID(id)))
                .message("Demon deleted!")
                .build()
        );
    }

    @PostMapping("/save/demon")
    public ResponseEntity<CustomResponse> saveDemon(@RequestBody @Valid Demon demon) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", demonService.saveDemon(demon)))
                .message("Demon saved!")
                .build()
        );
    }

    @PatchMapping("/update/demon/{id}")
    public ResponseEntity<CustomResponse> updateDemon(@PathVariable("id") Long id, @RequestBody @Valid Demon demon) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", demonService.updateDemon(id, demon)))
                .message("Demon updated!")
                .build()
        );
    }
}
