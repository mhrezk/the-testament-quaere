package com.testament.veltahleon.rest.entities.repo.spring.boot.data.jpa.repository.religion.mythology;

import com.testament.veltahleon.model.CustomResponse;
import com.testament.veltahleon.model.entities.religion.mythology.Deity;
import com.testament.veltahleon.services.entities.repo.ifc.religion.mythology.DeityService;
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
public class DeityController {

    @Autowired
    private DeityService deityService;

    @GetMapping("/deities")
    public ResponseEntity<CustomResponse> getPaginatedDeities(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Deity> deities = (List<Deity>) deityService.getDeitiesWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("paginatedDeities", deities))
                .message(deities.size() + " deities retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/deities/all")
    public ResponseEntity<CustomResponse> getAllDeities() {
        List<Deity> deities = (List<Deity>) deityService.getDeities();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("allDeities", deities))
                .message("All deities retrieved!")
                .build()
        );
    }

    @GetMapping("/deity/name")
    public ResponseEntity<CustomResponse> getDeityByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedDeityByName", deityService.getDeityByName(name)))
                .message("Deity retrieved!")
                .build()
        );
    }

    @GetMapping("/deity/{id}")
    public ResponseEntity<CustomResponse> getDeityByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedDeityByID", deityService.getDeityByID(id)))
                .message("Deity retrieved!")
                .build()
        );
    }

    @DeleteMapping("/delete/deity/{id}")
    public ResponseEntity<CustomResponse> deleteDeityByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("isDeityDeleted", deityService.deleteDeityByID(id)))
                .message("Deity deleted!")
                .build()
        );
    }

    @PostMapping("/save/deity")
    public ResponseEntity<CustomResponse> saveDeity(@RequestBody @Valid Deity deity) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("savedDeity", deityService.saveDeity(deity)))
                .message("Deity saved!")
                .build()
        );
    }

    @PatchMapping("/update/deity/{id}")
    public ResponseEntity<CustomResponse> updateDeity(@PathVariable("id") Long id, @RequestBody @Valid Deity deity) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("updatedDeity", deityService.updateDeity(id, deity)))
                .message("Deity updated!")
                .build()
        );
    }
}
