package com.testament.veltahleon.rest.history.library;

import com.testament.veltahleon.model.entities.history.library.Author;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.history.library.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

    public final String IMAGE_PATH = "src/main/resources/assets/images/authors/";

    @GetMapping("/authors")
    public ResponseEntity<CustomResponse> getPaginatedAuthors(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Author> authors = (List<Author>) authorService.getAuthorsWithPagination((pageNumber - 1), pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", authors))
                .message(authors.size() + " authors retrieved from page: " + pageNumber)
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

    @GetMapping("/authors/all/count")
    public ResponseEntity<CustomResponse> getAllAuthorsCount() {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", authorService.countAuthors()))
                .message("Count retrieved!")
                .build()
        );
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<CustomResponse> getAuthorByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", authorService.getAuthorByID(id)))
                .message("Author retrieved!")
                .build()
        );
    }

    @GetMapping(path = "/authors/images/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getAuthorImage(@PathVariable("imageName") String imageName) throws IOException {
        return Files.readAllBytes(Path.of(IMAGE_PATH + imageName));
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

    @PutMapping("/modify/author/{id}")
    public ResponseEntity<CustomResponse> modifyAuthor(@PathVariable("id") Long id, @RequestBody @Valid Author author) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", authorService.modifyAuthor(id, author)))
                .message("Author updated!")
                .build()
        );
    }
}
