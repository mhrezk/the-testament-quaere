package com.testament.veltahleon.rest.divination;

import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.model.entities.divination.Tarot;
import com.testament.veltahleon.services.ifc.divination.TarotService;
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
@RequestMapping("/divination")
@RequiredArgsConstructor
public class TarotController {

    @Autowired
    private TarotService tarotService;

    @GetMapping("/tarots")
    public ResponseEntity<CustomResponse> getPaginatedTarots(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Tarot> tarots = (List<Tarot>) tarotService.getTarotsWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", tarots))
                .message(tarots.size() + " tarots retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/tarots/all")
    public ResponseEntity<CustomResponse> getAllTarots() {
        List<Tarot> tarots = (List<Tarot>) tarotService.getTarots();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", tarots))
                .message("All tarots retrieved!")
                .build()
        );
    }

    @GetMapping("/tarot/{id}")
    public ResponseEntity<CustomResponse> getTarotByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", tarotService.getTarotByID(id)))
                .message("Tarot retrieved!")
                .build()
        );
    }

    @GetMapping(value = "/tarot/image/{fileName}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public byte[] getTarotImage(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get("src/main/java/com/testament/veltahleon/assets/images/tarots/" + fileName));
    }

    @DeleteMapping("/delete/tarot/{id}")
    public ResponseEntity<CustomResponse> deleteTarotByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", tarotService.deleteTarotByID(id)))
                .message("Tarot deleted!")
                .build()
        );
    }

    @PostMapping("/save/tarot")
    public ResponseEntity<CustomResponse> saveTarot(@RequestBody @Valid Tarot Tarot) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", tarotService.saveTarot(Tarot)))
                .message("Tarot saved!")
                .build()
        );
    }

    @PatchMapping("/update/tarot/{id}")
    public ResponseEntity<CustomResponse> updateTarot(@PathVariable("id") Long id, @RequestBody Tarot Tarot) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", tarotService.updateTarot(id, Tarot)))
                .message("Tarot updated!")
                .build()
        );
    }
}
