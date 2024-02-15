package com.testament.veltahleon.rest.places;

import com.testament.veltahleon.model.entities.places.NationType;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.places.NationTypeService;
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
public class NationTypeController {

    @Autowired
    private NationTypeService nationTypeService;

    @GetMapping("/nationTypes/all")
    public ResponseEntity<CustomResponse> getAllNationTypes() {
        List<NationType> nationTypes = (List<NationType>) nationTypeService.getNationTypes();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("allNationTypes", nationTypes))
                .message("All nationTypes retrieved!")
                .build()
        );
    }

    @GetMapping("/nationType/{id}")
    public ResponseEntity<CustomResponse> getNationTypeByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedNationTypeByID", nationTypeService.getNationTypeByID(id)))
                .message("NationType retrieved!")
                .build()
        );
    }

    @GetMapping("/nationType/name")
    public ResponseEntity<CustomResponse> getNationTypeByNationTypeName(@RequestParam(value = "nationTypeName") String nationTypeName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedNationTypeByName", nationTypeService.getNationTypeByType(nationTypeName)))
                .message("NationType retrieved!")
                .build()
        );
    }

    @PostMapping("/save/nationType")
    public ResponseEntity<CustomResponse> saveNationType(@RequestBody @Valid NationType nationType) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("savedNationType", nationTypeService.saveNationType(nationType)))
                .message("NationType saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/nationType/{id}")
    public ResponseEntity<CustomResponse> deleteNationTypeByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("isNationTypeDeleted", nationTypeService.deleteNationTypeByID(id)))
                .message("NationType deleted!")
                .build()
        );
    }

    @PatchMapping("/update/nationType/{id}")
    public ResponseEntity<CustomResponse> updateNationType(@PathVariable Long id, @RequestBody NationType nationType) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("updatedNationType", nationTypeService.updateNationType(id, nationType)))
                .message("NationType updated!")
                .build()
        );
    }
}
