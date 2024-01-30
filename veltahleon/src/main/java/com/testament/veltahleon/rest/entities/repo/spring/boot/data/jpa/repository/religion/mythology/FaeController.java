package com.testament.veltahleon.rest.entities.repo.spring.boot.data.jpa.repository.religion.mythology;

import com.testament.veltahleon.model.CustomResponse;
import com.testament.veltahleon.model.entities.religion.mythology.Fae;
import com.testament.veltahleon.services.entities.repo.ifc.religion.mythology.FaeService;
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
@RequestMapping("/religion")
@RequiredArgsConstructor
public class FaeController {

    @Autowired
    private FaeService faeService;

    @GetMapping("/faes")
    public ResponseEntity<CustomResponse> getPaginatedFaes(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Fae> faes = (List<Fae>) faeService.getFaesWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("paginatedFaes", faes))
                .message(faes.size() + " faes retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/faes/all")
    public ResponseEntity<CustomResponse> getAllFaes() {
        List<Fae> faes = (List<Fae>) faeService.getFaes();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("allFaes", faes))
                .message("All Faes retrieved!")
                .build()
        );
    }

    @GetMapping("/fae/name")
    public ResponseEntity<CustomResponse> getFaeByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedFaeByName", faeService.getFaeByName(name)))
                .message("Fae retrieved!")
                .build()
        );
    }

    @GetMapping("/fae/{id}")
    public ResponseEntity<CustomResponse> getFaeByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedFaeByID", faeService.getFaeByID(id)))
                .message("Fae retrieved!")
                .build()
        );
    }

    @GetMapping("/fae/race")
    public ResponseEntity<CustomResponse> getFaesByRacialName(@RequestParam("name") String name) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedFaesByRacialName", faeService.getFaesByRacialName(name)))
                .message("Faes retrieved!")
                .build()
        );
    }

    @DeleteMapping("/delete/fae/{id}")
    public ResponseEntity<CustomResponse> deleteFaeByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("isFaeDeleted", faeService.deleteFaeByID(id)))
                .message("Fae deleted!")
                .build()
        );
    }

    @PostMapping("/save/fae")
    public ResponseEntity<CustomResponse> saveFae(@RequestBody @Valid Fae fae) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("savedFae", faeService.saveFae(fae)))
                .message("Fae saved!")
                .build()
        );
    }

    @PatchMapping("/update/fae/{id}")
    public ResponseEntity<CustomResponse> updateFae(@PathVariable("id") Long id, @RequestBody @Valid Fae fae) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("updatedFae", faeService.updateFae(id, fae)))
                .message("Fae updated!")
                .build()
        );
    }
}
