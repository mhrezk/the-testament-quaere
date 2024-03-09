package com.testament.veltahleon.rest.religion;

import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.model.entities.religion.Prophet;
import com.testament.veltahleon.services.ifc.religion.ProphetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/religion")
@RequiredArgsConstructor
public class ProphetController {

    @Autowired
    private ProphetService prophetService;

    @GetMapping("/prophets")
    public ResponseEntity<CustomResponse> getPaginatedProphets(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Prophet> prophets = (List<Prophet>) prophetService.getProphetsWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", prophets))
                .message(prophets.size() + " prophets retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/prophets/sorted")
    public ResponseEntity<CustomResponse> getSortedPaginatedProphets(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Prophet> prophets = (List<Prophet>) prophetService.getProphetsSortedWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", prophets))
                .message(prophets.size() + " prophets retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/prophets/all")
    public ResponseEntity<CustomResponse> getAllProphets() {
        List<Prophet> prophets = (List<Prophet>) prophetService.getProphets();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", prophets))
                .message("All prophets retrieved!")
                .build()
        );
    }

    @GetMapping("/prophet/name")
    public ResponseEntity<CustomResponse> getProphetByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", prophetService.getProphetByName(name)))
                .message("Prophet retrieved!")
                .build()
        );
    }

    @GetMapping("/prophet/{id}")
    public ResponseEntity<CustomResponse> getProphetByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", prophetService.getProphetByID(id)))
                .message("Prophet retrieved!")
                .build()
        );
    }

    @DeleteMapping("/delete/prophet/{id}")
    public ResponseEntity<CustomResponse> deleteProphetByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", prophetService.deleteProphetByID(id)))
                .message("Prophet deleted!")
                .build()
        );
    }

    @DeleteMapping("/delete/prophet/name/{name}")
    public ResponseEntity<CustomResponse> deleteProphetByName(@PathVariable String name) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", prophetService.deleteProphetByName(name)))
                .message("Prophet deleted!")
                .build()
        );
    }

    @DeleteMapping("/delete/prophets")
    public ResponseEntity<CustomResponse> deleteAllProphets() {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", prophetService.deleteProphets()))
                .message("Prophets deleted!")
                .build()
        );
    }

    @PostMapping("/save/prophet")
    public ResponseEntity<CustomResponse> saveProphet(@RequestBody @Valid Prophet prophet) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", prophetService.saveProphet(prophet)))
                .message("Prophet saved!")
                .build()
        );
    }

    @PostMapping("/save/prophets")
    public ResponseEntity<CustomResponse> saveAllProphets(@RequestBody @Valid Collection<Prophet> prophets) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", prophetService.saveProphets(prophets)))
                .message("Prophets saved!")
                .build()
        );
    }

    @PatchMapping("/update/prophet/{id}")
    public ResponseEntity<CustomResponse> updateProphet(@PathVariable("id") Long id, @RequestBody @Valid Prophet prophet) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", prophetService.updateProphet(id, prophet)))
                .message("Prophet updated!")
                .build()
        );
    }
}
