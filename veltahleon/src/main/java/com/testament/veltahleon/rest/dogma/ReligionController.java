package com.testament.veltahleon.rest.dogma;

import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.model.entities.dogma.Religion;
import com.testament.veltahleon.services.ifc.dogma.ReligionService;
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
@RequestMapping("/dogma")
@RequiredArgsConstructor
public class ReligionController {

    @Autowired
    private ReligionService religionService;

    @GetMapping("/religions")
    public ResponseEntity<CustomResponse> getPaginatedReligions(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Religion> religions = (List<Religion>) religionService.getReligionsWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", religions))
                .message(religions.size() + " religions retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/religions/all")
    public ResponseEntity<CustomResponse> getAllReligions() {
        List<Religion> religions = (List<Religion>) religionService.getReligions();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", religions))
                .message("All religions retrieved!")
                .build()
        );
    }

    @GetMapping("/religion/name")
    public ResponseEntity<CustomResponse> getReligionByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", religionService.getReligionByName(name)))
                .message("Religion retrieved!")
                .build()
        );
    }

    @GetMapping("/religion/{id}")
    public ResponseEntity<CustomResponse> getReligionByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", religionService.getReligionByID(id)))
                .message("Religion retrieved!")
                .build()
        );
    }

    @DeleteMapping("/delete/religion/{id}")
    public ResponseEntity<CustomResponse> deleteReligionByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", religionService.deleteReligionByID(id)))
                .message("Religion deleted!")
                .build()
        );
    }

    @PostMapping("/save/religion")
    public ResponseEntity<CustomResponse> saveReligion(@RequestBody @Valid Religion religion) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", religionService.saveReligion(religion)))
                .message("Religion saved!")
                .build()
        );
    }

    @PatchMapping("/update/religion/{id}")
    public ResponseEntity<CustomResponse> updateReligion(@PathVariable("id") Long id, @RequestBody @Valid Religion religion) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", religionService.updateReligion(id, religion)))
                .message("Religion updated!")
                .build()
        );
    }
}
