package com.testament.veltahleon.rest.entities.repo.spring.boot.data.jpa.repository.religion.mythology;

import com.testament.veltahleon.model.CustomResponse;
import com.testament.veltahleon.model.entities.religion.mythology.Angel;
import com.testament.veltahleon.services.entities.repo.ifc.religion.mythology.AngelService;
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
public class AngelController {

    @Autowired
    private AngelService angelService;

    @GetMapping("/angels")
    public ResponseEntity<CustomResponse> getPaginatedAngels(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Angel> angels = (List<Angel>) angelService.getAngelsWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("paginatedAngels", angels))
                .message(angels.size() + " angels retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/angels/all")
    public ResponseEntity<CustomResponse> getAllAngels() {
        List<Angel> angels = (List<Angel>) angelService.getAngels();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("allAngels", angels))
                .message("All angels retrieved!")
                .build()
        );
    }

    @GetMapping("/angel/name")
    public ResponseEntity<CustomResponse> getAngelByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedAngelByName", angelService.getAngelByName(name)))
                .message("Angel retrieved!")
                .build()
        );
    }

    @GetMapping("/angel/{id}")
    public ResponseEntity<CustomResponse> getAngelByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedAngelByID", angelService.getAngelByID(id)))
                .message("Angel retrieved!")
                .build()
        );
    }

    @DeleteMapping("/delete/angel/{id}")
    public ResponseEntity<CustomResponse> deleteAngelByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("isAngelDeleted", angelService.deleteAngelByID(id)))
                .message("Angel deleted!")
                .build()
        );
    }

    @PostMapping("/save/angel")
    public ResponseEntity<CustomResponse> saveAngel(@RequestBody @Valid Angel angel) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("savedAngel", angelService.saveAngel(angel)))
                .message("Angel saved!")
                .build()
        );
    }

    @PatchMapping("/update/angel/{id}")
    public ResponseEntity<CustomResponse> updateAngel(@PathVariable("id") Long id, @RequestBody @Valid Angel angel) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("updatedAngel", angelService.updateAngel(id, angel)))
                .message("Angel updated!")
                .build()
        );
    }
}
