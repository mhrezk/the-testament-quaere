package com.testament.veltahleon.rest.history.library;

import com.testament.veltahleon.dto.history.library.ChapterDTO;
import com.testament.veltahleon.mappers.history.library.ChapterMapper;
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

    @Autowired
    private ChapterMapper chapterMapper;

    @GetMapping("/chapters/{bookName}")
    public ResponseEntity<CustomResponse> getPaginatedChaptersByBookName(@PathVariable(value = "bookName") String bookName, @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Chapter> chapters = (List<Chapter>) chapterService.getPaginatedChaptersByBookName(bookName, (pageNumber - 1), pageSize);
        List<ChapterDTO> chaptersDTO = chapters.stream().map(c -> chapterMapper.convertToDTO(c)).toList();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", chaptersDTO))
                .message(chapters.size() + " chapters retrieved from page: " + pageNumber)
                .build()
        );
    }

    @GetMapping("/chapters/{bookName}/all")
    public ResponseEntity<CustomResponse> getChaptersByBookName(@PathVariable(value = "bookName") String bookName) {
        List<Chapter> chapters = (List<Chapter>) chapterService.getChaptersByBookName(bookName);
        List<ChapterDTO> chaptersDTO = chapters.stream().map(c -> chapterMapper.convertToDTO(c)).toList();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", chaptersDTO))
                .message(chapters.size() + " chapters retrieved")
                .build()
        );
    }

    @GetMapping("/chapters/all")
    public ResponseEntity<CustomResponse> getAllChapters() {
        List<Chapter> chapters = (List<Chapter>) chapterService.getChapters();
        List<ChapterDTO> chaptersDTO = chapters.stream().map(c -> chapterMapper.convertToDTO(c)).toList();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", chaptersDTO))
                .message("All chapters retrieved!")
                .build()
        );
    }

    @GetMapping("/chapters/{bookName}/count")
    public ResponseEntity<CustomResponse> getAllChaptersByBookName(@PathVariable(value = "bookName") String bookName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", chapterService.getNumberOfChaptersPerBook(bookName)))
                .message("Count retrieved!")
                .build()
        );
    }

    @GetMapping("/chapter/{id}")
    public ResponseEntity<CustomResponse> getChapterByID(@PathVariable Long id) {
        ChapterDTO chapterDTO = chapterMapper.convertToDTO(chapterService.getChapterByID(id));
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", chapterDTO))
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

    @PostMapping("/save/chapter/{bookName}")
    public ResponseEntity<CustomResponse> saveChapter(@PathVariable(value = "bookName") String bookName, @RequestBody @Valid ChapterDTO chapterDTO) {
        Chapter chapter = chapterMapper.convertToEntity(chapterDTO);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", chapterMapper.convertToDTO(chapterService.saveChapter(chapter, bookName))))
                .message("Chapter saved!")
                .build()
        );
    }

    @PatchMapping("/update/chapter/{id}")
    public ResponseEntity<CustomResponse> updateChapter(@PathVariable("id") Long id, @RequestBody @Valid ChapterDTO chapterDTO) {
        Chapter chapter = chapterMapper.convertToEntity(chapterDTO);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", chapterMapper.convertToDTO(chapterService.updateChapter(id, chapter))))
                .message("Chapter updated!")
                .build()
        );
    }

    @PutMapping("/modify/chapter/{id}")
    public ResponseEntity<CustomResponse> modifyChapter(@PathVariable("id") Long id, @RequestBody @Valid ChapterDTO chapterDTO) {
        Chapter chapter = chapterMapper.convertToEntity(chapterDTO);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", chapterMapper.convertToDTO(chapterService.modifyChapter(id, chapter))))
                .message("Chapter updated!")
                .build()
        );
    }
}
