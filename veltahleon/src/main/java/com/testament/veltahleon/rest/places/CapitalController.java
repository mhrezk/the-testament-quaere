package com.testament.veltahleon.rest.places;

import com.testament.veltahleon.model.entities.places.Capital;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.places.CapitalService;
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
public class CapitalController {

    @Autowired
    private CapitalService capitalService;

    @GetMapping("/capitals")
    public ResponseEntity<CustomResponse> getPaginatedCapitals(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Capital> capitals = (List<Capital>) capitalService.getCapitalsWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("paginatedCapitals", capitals))
                .message(capitals.size() + " capitals retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/capitals/all")
    public ResponseEntity<CustomResponse> getAllCapitals() {
        List<Capital> capitals = (List<Capital>) capitalService.getCapitals();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("allCapitals", capitals))
                .message("All capitals retrieved!")
                .build()
        );
    }

    @GetMapping("/capital/{id}")
    public ResponseEntity<CustomResponse> getCapitalByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedCapitalByID", capitalService.getCapitalByID(id)))
                .message("Capital retrieved!")
                .build()
        );
    }

    @GetMapping("/capital/name")
    public ResponseEntity<CustomResponse> getCapitalByCapitalName(@RequestParam(value = "capitalName") String capitalName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedCapitalByName", capitalService.getCapitalByName(capitalName)))
                .message("Capital retrieved!")
                .build()
        );
    }

    @PostMapping("/save/capital")
    public ResponseEntity<CustomResponse> saveCapital(@RequestBody @Valid Capital capital) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("savedCapital", capitalService.saveCapital(capital)))
                .message("Capital saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/capital/{id}")
    public ResponseEntity<CustomResponse> deleteCapitalByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("isCapitalDeleted", capitalService.deleteCapitalByID(id)))
                .message("Capital deleted!")
                .build()
        );
    }

    @PatchMapping("/update/capital/{id}")
    public ResponseEntity<CustomResponse> updateCapital(@PathVariable Long id, @RequestBody Capital capital) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("updatedCapital", capitalService.updateCapital(id, capital)))
                .message("Capital updated!")
                .build()
        );
    }
}
