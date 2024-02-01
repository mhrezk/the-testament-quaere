package com.testament.veltahleon.rest.entities.repo.spring.boot.data.jpa.repository.history.library;

import com.testament.veltahleon.model.entities.history.library.Excerpt;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.entities.repo.ifc.history.library.ExcerptService;
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
@RequestMapping("/history/library")
@RequiredArgsConstructor
public class ExcerptController {

    @Autowired
    private ExcerptService excerptService;

    @GetMapping("/excerpts")
    public ResponseEntity<CustomResponse> getPaginatedExcerpts(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Excerpt> excerpts = (List<Excerpt>) excerptService.getExcerptsWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("paginatedExcerpts", excerpts))
                .message(excerpts.size() + " excerpts retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/excerpts/all")
    public ResponseEntity<CustomResponse> getAllExcerpts() {
        List<Excerpt> excerpts = (List<Excerpt>) excerptService.getExcerpts();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("allExcerpts", excerpts))
                .message("All excerpts retrieved!")
                .build()
        );
    }

    @GetMapping("/excerpt/{id}")
    public ResponseEntity<CustomResponse> getExcerptByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedExcerptByID", excerptService.getExcerptByID(id)))
                .message("Excerpt retrieved!")
                .build()
        );
    }

    @DeleteMapping("/delete/excerpt/{id}")
    public ResponseEntity<CustomResponse> deleteExcerptByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("isExcerptDeleted", excerptService.deleteExcerptByID(id)))
                .message("Excerpt deleted!")
                .build()
        );
    }

    @PostMapping("/save/excerpt")
    public ResponseEntity<CustomResponse> saveExcerpt(@RequestBody @Valid Excerpt excerpt) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("savedExcerpt", excerptService.saveExcerpt(excerpt)))
                .message("Excerpt saved!")
                .build()
        );
    }

    @PatchMapping("/update/excerpt/{id}")
    public ResponseEntity<CustomResponse> updateExcerpt(@PathVariable("id") Long id, @RequestBody @Valid Excerpt excerpt) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("updatedExcerpt", excerptService.updateExcerpt(id, excerpt)))
                .message("Excerpt updated!")
                .build()
        );
    }
}
