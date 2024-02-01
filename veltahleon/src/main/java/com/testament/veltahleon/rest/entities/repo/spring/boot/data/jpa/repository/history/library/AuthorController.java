package com.testament.veltahleon.rest.entities.repo.spring.boot.data.jpa.repository.history.library;

import com.testament.veltahleon.model.entities.history.library.Author;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.entities.repo.ifc.history.library.AuthorService;
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
                .data(Map.of("paginatedAuthors", authors))
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
                .data(Map.of("allAuthors", authors))
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
                .data(Map.of("queriedAuthorByID", authorService.getAuthorByID(id)))
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
                .data(Map.of("isAuthorDeleted", authorService.deleteAuthorByID(id)))
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
                .data(Map.of("savedAuthor", authorService.saveAuthor(author)))
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
                .data(Map.of("updatedAuthor", authorService.updateAuthor(id, author)))
                .message("Author updated!")
                .build()
        );
    }
}