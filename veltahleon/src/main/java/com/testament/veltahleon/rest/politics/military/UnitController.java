package com.testament.veltahleon.rest.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Unit;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.politics.military.UnitService;
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
public class UnitController {

    @Autowired
    private UnitService unitService;

    @GetMapping("/units")
    public ResponseEntity<CustomResponse> getPaginatedUnits(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Unit> units = (List<Unit>) unitService.getUnitsWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("paginatedUnits", units))
                .message(units.size() + " units retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/units/all")
    public ResponseEntity<CustomResponse> getAllUnits() {
        List<Unit> units = (List<Unit>) unitService.getUnits();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("allUnits", units))
                .message("All units retrieved!")
                .build()
        );
    }

    @GetMapping("/unit/{id}")
    public ResponseEntity<CustomResponse> getUnitByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedUnitByID", unitService.getUnitByID(id)))
                .message("Unit retrieved!")
                .build()
        );
    }

    @GetMapping("/unit/type")
    public ResponseEntity<CustomResponse> getUnitByUnitType(@RequestParam(value = "type") String type) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedUnitByName", unitService.getUnitByUnitType(type)))
                .message("Unit retrieved!")
                .build()
        );
    }

    @PostMapping("/save/unit")
    public ResponseEntity<CustomResponse> saveUnit(@RequestBody @Valid Unit unit) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("savedUnit", unitService.saveUnit(unit)))
                .message("Unit saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/unit/{id}")
    public ResponseEntity<CustomResponse> deleteUnitByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("isUnitDeleted", unitService.deleteUnitByID(id)))
                .message("Unit deleted!")
                .build()
        );
    }

    @PatchMapping("/update/unit/{id}")
    public ResponseEntity<CustomResponse> updateUnit(@PathVariable Long id, @RequestBody @Valid Unit unit) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("updatedUnit", unitService.updateUnit(id, unit)))
                .message("Unit updated!")
                .build()
        );
    }
}
