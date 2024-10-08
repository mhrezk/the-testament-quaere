package com.testament.veltahleon.rest.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Squad;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.politics.military.SquadService;
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
@RequestMapping("/politics/military")
@RequiredArgsConstructor
public class SquadController {

    @Autowired
    private SquadService squadService;

    @GetMapping("/squads")
    public ResponseEntity<CustomResponse> getPaginatedSquads(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Squad> squads = (List<Squad>) squadService.getSquadsWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", squads))
                .message(squads.size() + " squads retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/squads/all")
    public ResponseEntity<CustomResponse> getAllSquads() {
        List<Squad> squads = (List<Squad>) squadService.getSquads();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", squads))
                .message("All squads retrieved!")
                .build()
        );
    }

    @GetMapping("/squad/{id}")
    public ResponseEntity<CustomResponse> getSquadByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", squadService.getSquadByID(id)))
                .message("Squad retrieved!")
                .build()
        );
    }

    @GetMapping("/squad/leader")
    public ResponseEntity<CustomResponse> getSquadBySquadName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", squadService.getSquadBySquadLeader(name)))
                .message("Squad retrieved!")
                .build()
        );
    }

    @GetMapping(value = "/squad/image/{fileName}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public byte[] getSquadSymbol(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get("src/main/java/com/testament/veltahleon/assets/images/symbols/" + fileName));
    }

    @PostMapping("/save/squad")
    public ResponseEntity<CustomResponse> saveSquad(@RequestBody @Valid Squad squad) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", squadService.saveSquad(squad)))
                .message("Squad saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/squad/{id}")
    public ResponseEntity<CustomResponse> deleteSquadByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", squadService.deleteSquadByID(id)))
                .message("Squad deleted!")
                .build()
        );
    }

    @PatchMapping("/update/squad/{id}")
    public ResponseEntity<CustomResponse> updateSquad(@PathVariable Long id, @RequestBody @Valid Squad squad) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", squadService.updateSquad(id, squad)))
                .message("Squad updated!")
                .build()
        );
    }
}
