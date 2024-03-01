package com.testament.veltahleon.rest.history.library;

import com.testament.veltahleon.model.entities.history.library.Chapter;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.history.library.ChapterService;
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
@RequestMapping("/history/library")
@RequiredArgsConstructor
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @GetMapping("/chapters")
    public ResponseEntity<CustomResponse> getPaginatedChapters(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Chapter> chapters = (List<Chapter>) chapterService.getChaptersWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", chapters))
                .message(chapters.size() + " chapters retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/chapters/all")
    public ResponseEntity<CustomResponse> getAllChapters() {
        List<Chapter> chapters = (List<Chapter>) chapterService.getChapters();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", chapters))
                .message("All chapters retrieved!")
                .build()
        );
    }

    @GetMapping("/chapter/{id}")
    public ResponseEntity<CustomResponse> getChapterByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", chapterService.getChapterByID(id)))
                .message("Chapter retrieved!")
                .build()
        );
    }

    @DeleteMapping("/delete/chapter/{id}")
    public ResponseEntity<CustomResponse> deleteChapterByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", chapterService.deleteChapterByID(id)))
                .message("Chapter deleted!")
                .build()
        );
    }

    @PostMapping("/save/chapter")
    public ResponseEntity<CustomResponse> saveChapter(@RequestBody @Valid Chapter chapter) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", chapterService.saveChapter(chapter)))
                .message("Chapter saved!")
                .build()
        );
    }

    @PatchMapping("/update/chapter/{id}")
    public ResponseEntity<CustomResponse> updateChapter(@PathVariable("id") Long id, @RequestBody @Valid Chapter chapter) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", chapterService.updateChapter(id, chapter)))
                .message("Chapter updated!")
                .build()
        );
    }
}
