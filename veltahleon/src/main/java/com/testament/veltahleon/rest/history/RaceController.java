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
import java.nio.file.Path;
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

    public final String imagePath = "src/main/resources/assets/images/races/";

    @GetMapping("/races")
    public ResponseEntity<CustomResponse> getPaginatedRaces(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Race> races = (List<Race>) raceService.getRacesWithPagination((pageNumber - 1), pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", races))
                .message(races.size() + " races retrieved from page: " + pageNumber)
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

    @GetMapping("/races/all/count")
    public ResponseEntity<CustomResponse> getAllRacesCount() {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", raceService.countRaces()))
                .message("Count retrieved!")
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

    @GetMapping("/race/{raceName}/exist")
    public ResponseEntity<CustomResponse> getRaceNameExistence(@PathVariable(value = "raceName") String raceName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", raceService.doesRaceNameExist(raceName)))
                .message("Race name exists!")
                .build()
        );
    }

    @GetMapping(path = "/races/images/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getRacialImage(@PathVariable("imageName") String imageName) throws IOException {
        return Files.readAllBytes(Path.of(imagePath + imageName));
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
    public ResponseEntity<CustomResponse> saveRace(@RequestBody @Valid Race race) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", raceService.saveRace(race)))
                .message("Race saved!")
                .build()
        );
    }

    @PatchMapping("/update/race/{id}")
    public ResponseEntity<CustomResponse> updateRace(@PathVariable("id") Long id, @RequestBody @Valid Race race) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", raceService.updateRace(id, race)))
                .message("Race updated!")
                .build()
        );
    }

    @PutMapping("/modify/race/{id}")
    public ResponseEntity<CustomResponse> modifyRace(@PathVariable Long id, @RequestBody Race race) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", raceService.modifyRace(id, race)))
                .message("Race updated!")
                .build()
        );
    }
}
