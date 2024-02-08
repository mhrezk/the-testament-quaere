package com.testament.veltahleon.rest.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Squad;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.politics.military.SquadService;
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
                .data(Map.of("paginatedSquads", squads))
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
                .data(Map.of("allSquads", squads))
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
                .data(Map.of("queriedSquadByID", squadService.getSquadByID(id)))
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
                .data(Map.of("queriedSquadByName", squadService.getSquadBySquadLeader(name)))
                .message("Squad retrieved!")
                .build()
        );
    }

    @PostMapping("/save/squad")
    public ResponseEntity<CustomResponse> saveSquad(@RequestBody @Valid Squad squad) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("savedSquad", squadService.saveSquad(squad)))
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
                .data(Map.of("isSquadDeleted", squadService.deleteSquadByID(id)))
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
                .data(Map.of("updatedSquad", squadService.updateSquad(id, squad)))
                .message("Squad updated!")
                .build()
        );
    }
}
