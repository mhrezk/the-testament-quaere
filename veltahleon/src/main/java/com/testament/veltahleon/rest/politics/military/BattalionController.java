package com.testament.veltahleon.rest.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Battalion;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.politics.military.BattalionService;
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
@RequestMapping("/politics/military")
@RequiredArgsConstructor
public class BattalionController {

    @Autowired
    private BattalionService battalionService;

    @GetMapping("/battalions")
    public ResponseEntity<CustomResponse> getPaginatedBattalions(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Battalion> battalions = (List<Battalion>) battalionService.getBattalionsWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", battalions))
                .message(battalions.size() + " battalions retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/battalions/all")
    public ResponseEntity<CustomResponse> getAllBattalions() {
        List<Battalion> battalions = (List<Battalion>) battalionService.getBattalions();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", battalions))
                .message("All battalions retrieved!")
                .build()
        );
    }

    @GetMapping("/battalion/{id}")
    public ResponseEntity<CustomResponse> getBattalionByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", battalionService.getBattalionByID(id)))
                .message("Battalion retrieved!")
                .build()
        );
    }

    @GetMapping("/battalion/name")
    public ResponseEntity<CustomResponse> getBattalionByBattalionName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", battalionService.getBattalionByName(name)))
                .message("Battalion retrieved!")
                .build()
        );
    }

    @PostMapping("/save/battalion")
    public ResponseEntity<CustomResponse> saveBattalion(@RequestBody @Valid Battalion battalion) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", battalionService.saveBattalion(battalion)))
                .message("Battalion saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/battalion/{id}")
    public ResponseEntity<CustomResponse> deleteBattalionByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", battalionService.deleteBattalionByID(id)))
                .message("Battalion deleted!")
                .build()
        );
    }

    @PatchMapping("/update/battalion/{id}")
    public ResponseEntity<CustomResponse> updateBattalion(@PathVariable Long id, @RequestBody @Valid Battalion battalion) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", battalionService.updateBattalion(id, battalion)))
                .message("Battalion updated!")
                .build()
        );
    }
}
