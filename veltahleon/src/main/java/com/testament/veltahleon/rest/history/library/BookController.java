package com.testament.veltahleon.rest.history.library;

import com.testament.veltahleon.model.entities.history.library.Book;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.history.library.BookService;
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
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<CustomResponse> getPaginatedBooks(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Book> books = (List<Book>) bookService.getBooksWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("paginatedBooks", books))
                .message(books.size() + " books retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/books/all")
    public ResponseEntity<CustomResponse> getAllBooks() {
        List<Book> books = (List<Book>) bookService.getBooks();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("allBooks", books))
                .message("All books retrieved!")
                .build()
        );
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<CustomResponse> getBookByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedBookByID", bookService.getBookByID(id)))
                .message("Book retrieved!")
                .build()
        );
    }

    @DeleteMapping("/delete/book/{id}")
    public ResponseEntity<CustomResponse> deleteBookByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("isBookDeleted", bookService.deleteBookByID(id)))
                .message("Book deleted!")
                .build()
        );
    }

    @PostMapping("/save/book")
    public ResponseEntity<CustomResponse> saveBook(@RequestBody @Valid Book book) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("savedBook", bookService.saveBook(book)))
                .message("Book saved!")
                .build()
        );
    }

    @PatchMapping("/update/book/{id}")
    public ResponseEntity<CustomResponse> updateBook(@PathVariable("id") Long id, @RequestBody @Valid Book book) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("updatedBook", bookService.updateBook(id, book)))
                .message("Book updated!")
                .build()
        );
    }
}
