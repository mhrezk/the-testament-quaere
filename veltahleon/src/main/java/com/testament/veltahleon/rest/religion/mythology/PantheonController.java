package com.testament.veltahleon.rest.religion.mythology;

import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.model.entities.religion.mythology.Pantheon;
import com.testament.veltahleon.services.ifc.religion.mythology.PantheonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/religion")
@RequiredArgsConstructor
public class PantheonController {

    @Autowired
    private PantheonService pantheonService;

    @GetMapping("/pantheons")
    public ResponseEntity<CustomResponse> getPaginatedPantheons(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Pantheon> pantheons = (List<Pantheon>) pantheonService.getPantheonsWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("paginatedPantheons", pantheons))
                .message(pantheons.size() + " pantheons retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/pantheons/all")
    public ResponseEntity<CustomResponse> getAllPantheons() {
        List<Pantheon> pantheons = (List<Pantheon>) pantheonService.getPantheons();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("allPantheons", pantheons))
                .message("All pantheons retrieved!")
                .build()
        );
    }

    @GetMapping("/pantheon/{id}")
    public ResponseEntity<CustomResponse> getPantheonByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedPantheonByID", pantheonService.getPantheonByID(id)))
                .message("Pantheon retrieved!")
                .build()
        );
    }

    @DeleteMapping("/delete/pantheon/{id}")
    public ResponseEntity<CustomResponse> deletePantheonByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("isPantheonDeleted", pantheonService.deletePantheonByID(id)))
                .message("Pantheon deleted!")
                .build()
        );
    }

    @PostMapping("/save/pantheon")
    public ResponseEntity<CustomResponse> savePantheon(@RequestBody @Valid Pantheon pantheon) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("savedPantheon", pantheonService.savePantheon(pantheon)))
                .message("Pantheon saved!")
                .build()
        );
    }

    @PatchMapping("/update/pantheon/{id}")
    public ResponseEntity<CustomResponse> updatePantheon(@PathVariable("id") Long id, @RequestBody @Valid Pantheon pantheon) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("updatedPantheon", pantheonService.updatePantheon(id, pantheon)))
                .message("Pantheon updated!")
                .build()
        );
    }
}
