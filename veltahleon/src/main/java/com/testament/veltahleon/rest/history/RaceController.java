package com.testament.veltahleon.rest.history;

import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.model.entities.history.Race;
import com.testament.veltahleon.services.ifc.history.RaceService;
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
@RequestMapping("/history")
@RequiredArgsConstructor
public class RaceController {

    @Autowired
    private RaceService raceService;

    @GetMapping("/races")
    public ResponseEntity<CustomResponse> getPaginatedRaces(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Race> races = (List<Race>) raceService.getRacesWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", races))
                .message(races.size() + " races retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/races/all")
    public ResponseEntity<CustomResponse> getAllRaces() {
        List<Race> races = (List<Race>) raceService.getRaces();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", races))
                .message("All races retrieved!")
                .build()
        );
    }

    @GetMapping("/race/{id}")
    public ResponseEntity<CustomResponse> getRaceByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", raceService.getRaceByID(id)))
                .message("Race retrieved!")
                .build()
        );
    }

    @GetMapping("/race")
    public ResponseEntity<CustomResponse> getRaceByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", raceService.getRaceByName(name)))
                .message("Race retrieved!")
                .build()
        );
    }

    @GetMapping(value = "/race/image/{fileName}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public byte[] getRaceImage(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get("src/main/java/com/testament/veltahleon/assets/images/races/" + fileName));
    }

    @DeleteMapping("/delete/race/{id}")
    public ResponseEntity<CustomResponse> deleteRaceByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", raceService.deleteRaceByID(id)))
                .message("Race deleted!")
                .build()
        );
    }

    @PostMapping("/save/race")
    public ResponseEntity<CustomResponse> saveRace(@RequestBody @Valid Race Race) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", raceService.saveRace(Race)))
                .message("Race saved!")
                .build()
        );
    }

    @PatchMapping("/update/race/{id}")
    public ResponseEntity<CustomResponse> updateRace(@PathVariable("id") Long id, @RequestBody @Valid Race Race) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", raceService.updateRace(id, Race)))
                .message("Race updated!")
                .build()
        );
    }

    @PutMapping("/update/race")
    public ResponseEntity<CustomResponse> updateRace(@RequestBody Race race) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", raceService.updateRace(race)))
                .message("Race updated!")
                .build()
        );
    }
}
