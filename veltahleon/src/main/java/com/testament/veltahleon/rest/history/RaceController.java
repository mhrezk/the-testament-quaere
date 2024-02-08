package com.testament.veltahleon.rest.history;

import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.model.entities.history.Race;
import com.testament.veltahleon.services.ifc.history.RaceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
                .data(Map.of("paginatedRaces", races))
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
                .data(Map.of("allRaces", races))
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
                .data(Map.of("queriedRaceByID", raceService.getRaceByID(id)))
                .message("Race retrieved!")
                .build()
        );
    }

    @DeleteMapping("/delete/race/{id}")
    public ResponseEntity<CustomResponse> deleteRaceByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("isRaceDeleted", raceService.deleteRaceByID(id)))
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
                .data(Map.of("savedRace", raceService.saveRace(Race)))
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
                .data(Map.of("updatedRace", raceService.updateRace(id, Race)))
                .message("Race updated!")
                .build()
        );
    }
}
