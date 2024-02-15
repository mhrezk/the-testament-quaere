package com.testament.veltahleon.rest.places;

import com.testament.veltahleon.model.entities.places.Continent;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.places.ContinentService;
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
@RequestMapping("/places")
@RequiredArgsConstructor
public class ContinentController {
    
    @Autowired
    private ContinentService continentService;

    @GetMapping("/continents")
    public ResponseEntity<CustomResponse> getPaginatedContinents(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Continent> continents = (List<Continent>) continentService.getContinentsWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("paginatedContinents", continents))
                .message(continents.size() + " continents retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/continents/all")
    public ResponseEntity<CustomResponse> getAllContinents() {
        List<Continent> continents = (List<Continent>) continentService.getContinents();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("allContinents", continents))
                .message("All continents retrieved!")
                .build()
        );
    }

    @GetMapping("/continent/{id}")
    public ResponseEntity<CustomResponse> getContinentByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedContinentByID", continentService.getContinentByID(id)))
                .message("Continent retrieved!")
                .build()
        );
    }

    @GetMapping("/continent/name")
    public ResponseEntity<CustomResponse> getContinentsByContinentName(@RequestParam(value = "continentName") String continentName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedContinentByName", continentService.getContinentByName(continentName)))
                .message("Continent retrieved!")
                .build()
        );
    }

    @PostMapping("/save/continent")
    public ResponseEntity<CustomResponse> saveContinent(@RequestBody @Valid Continent continent) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("savedContinent", continentService.saveContinent(continent)))
                .message("Continent saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/continent/{id}")
    public ResponseEntity<CustomResponse> deleteContinentByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("isContinentDeleted", continentService.deleteContinentByID(id)))
                .message("Continent deleted!")
                .build()
        );
    }

    @PatchMapping("/update/continent/{id}")
    public ResponseEntity<CustomResponse> updateContinent(@PathVariable Long id, @RequestBody Continent continent) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("updatedContinent", continentService.updateContinent(id, continent)))
                .message("Continent updated!")
                .build()
        );
    }
}
