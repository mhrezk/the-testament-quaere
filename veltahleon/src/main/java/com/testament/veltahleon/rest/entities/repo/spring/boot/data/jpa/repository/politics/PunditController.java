package com.testament.veltahleon.rest.entities.repo.spring.boot.data.jpa.repository.politics;

import com.testament.veltahleon.model.entities.politics.Pundit;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.entities.repo.ifc.politics.PunditService;
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
@RequestMapping("/politics")
@RequiredArgsConstructor
public class PunditController {

    @Autowired
    private PunditService punditService;

    @GetMapping("/pundits")
    public ResponseEntity<CustomResponse> getPaginatedPundits(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Pundit> pundits = (List<Pundit>) punditService.getPunditsWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("paginatedPundits", pundits))
                .message(pundits.size() + " pundits retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/pundits/all")
    public ResponseEntity<CustomResponse> getAllPundits() {
        List<Pundit> pundits = (List<Pundit>) punditService.getPundits();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("allPundits", pundits))
                .message("All pundits retrieved!")
                .build()
        );
    }

    @GetMapping("/pundit/{id}")
    public ResponseEntity<CustomResponse> getPunditByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedPunditByID", punditService.getPunditByID(id)))
                .message("Pundit retrieved!")
                .build()
        );
    }

    @GetMapping("/pundit/name")
    public ResponseEntity<CustomResponse> getPunditByPunditName(@RequestParam(value = "punditName") String punditName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedPunditByName", punditService.getPunditByName(punditName)))
                .message("Pundit retrieved!")
                .build()
        );
    }

    @PostMapping("/save/pundit")
    public ResponseEntity<CustomResponse> savePundit(@RequestBody @Valid Pundit pundit) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("savedPundit", punditService.savePundit(pundit)))
                .message("Pundit saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/pundit/{id}")
    public ResponseEntity<CustomResponse> deletePunditByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("isPunditDeleted", punditService.deletePunditByID(id)))
                .message("Pundit deleted!")
                .build()
        );
    }

    @PatchMapping("/update/pundit/{id}")
    public ResponseEntity<CustomResponse> updatePundit(@PathVariable Long id, @RequestBody @Valid Pundit pundit) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("updatedPundit", punditService.updatePundit(id, pundit)))
                .message("Pundit updated!")
                .build()
        );
    }
}
