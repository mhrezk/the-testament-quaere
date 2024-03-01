package com.testament.veltahleon.rest.history.library;

import com.testament.veltahleon.model.entities.history.library.Author;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.history.library.AuthorService;
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
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/authors")
    public ResponseEntity<CustomResponse> getPaginatedAuthors(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Author> authors = (List<Author>) authorService.getAuthorsWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", authors))
                .message(authors.size() + " authors retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/authors/all")
    public ResponseEntity<CustomResponse> getAllAuthors() {
        List<Author> authors = (List<Author>) authorService.getAuthors();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", authors))
                .message("All authors retrieved!")
                .build()
        );
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<CustomResponse> getAuthorByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", authorService.getAuthorByID(id)))
                .message("Author retrieved!")
                .build()
        );
    }

    @DeleteMapping("/delete/author/{id}")
    public ResponseEntity<CustomResponse> deleteAuthorByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", authorService.deleteAuthorByID(id)))
                .message("Author deleted!")
                .build()
        );
    }

    @PostMapping("/save/author")
    public ResponseEntity<CustomResponse> saveAuthor(@RequestBody @Valid Author author) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", authorService.saveAuthor(author)))
                .message("Author saved!")
                .build()
        );
    }

    @PatchMapping("/update/author/{id}")
    public ResponseEntity<CustomResponse> updateAuthor(@PathVariable("id") Long id, @RequestBody @Valid Author author) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", authorService.updateAuthor(id, author)))
                .message("Author updated!")
                .build()
        );
    }
}
