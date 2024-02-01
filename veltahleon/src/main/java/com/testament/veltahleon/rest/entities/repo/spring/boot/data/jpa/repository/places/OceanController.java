package com.testament.veltahleon.rest.entities.repo.spring.boot.data.jpa.repository.places;

import com.testament.veltahleon.model.entities.places.Ocean;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.entities.repo.ifc.places.OceanService;
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
public class OceanController {

    @Autowired
    private OceanService oceanService;

    @GetMapping("/oceans/all")
    public ResponseEntity<CustomResponse> getAllOceans() {
        List<Ocean> oceans = (List<Ocean>) oceanService.getOceans();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("allOceans", oceans))
                .message("All oceans retrieved!")
                .build()
        );
    }

    @GetMapping("/ocean/{id}")
    public ResponseEntity<CustomResponse> getOceanByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedOceanByID", oceanService.getOceanByID(id)))
                .message("Ocean retrieved!")
                .build()
        );
    }

    @GetMapping("/ocean/name")
    public ResponseEntity<CustomResponse> getOceanByOceanName(@RequestParam(value = "oceanName") String oceanName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedOceanByName", oceanService.getOceanByName(oceanName)))
                .message("Ocean retrieved!")
                .build()
        );
    }

    @PostMapping("/save/ocean")
    public ResponseEntity<CustomResponse> saveOcean(@RequestBody @Valid Ocean ocean) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("savedOcean", oceanService.saveOcean(ocean)))
                .message("Ocean saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/ocean/{id}")
    public ResponseEntity<CustomResponse> deleteOceanByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("isOceanDeleted", oceanService.deleteOceanByID(id)))
                .message("Ocean deleted!")
                .build()
        );
    }

    @PatchMapping("/update/ocean/{id}")
    public ResponseEntity<CustomResponse> updateOcean(@PathVariable Long id, @RequestBody @Valid Ocean ocean) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("updatedOcean", oceanService.updateOcean(id, ocean)))
                .message("Ocean updated!")
                .build()
        );
    }
}
