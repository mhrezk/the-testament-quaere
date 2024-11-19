package com.testament.veltahleon.rest.history.library;

import com.testament.veltahleon.dto.history.library.BookDTO;
import com.testament.veltahleon.mappers.history.library.BookMapper;
import com.testament.veltahleon.model.entities.history.library.Book;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.history.library.BookService;
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
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookMapper bookMapper;

    public final String IMAGE_PATH = "src/main/resources/assets/images/books/";

    @GetMapping("/books")
    public ResponseEntity<CustomResponse> getPaginatedBooks(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Book> books = (List<Book>) bookService.getBooksWithPagination((pageNumber - 1), pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", books))
                .message(books.size() + " books retrieved from page: " + pageNumber)
                .build()
        );
    }

    @GetMapping("/books/{id}/{firstName}/{lastName}")
    public ResponseEntity<CustomResponse> getPaginatedBooksByAuthorName(@PathVariable Long id, @PathVariable(value = "firstName") String firstName, @PathVariable(value = "lastName") String lastName, @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Book> books = (List<Book>) bookService.getBooksWithPaginationByAuthorName(id, firstName, lastName, (pageNumber - 1), pageSize);
        List<BookDTO> booksDTO = books.stream().map(b -> bookMapper.convertToDTO(b)).toList();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", booksDTO))
                .message(books.size() + " books retrieved from page: " + pageNumber)
                .build()
        );
    }

    @GetMapping("/books/all")
    public ResponseEntity<CustomResponse> getAllBooks() {
        List<Book> books = (List<Book>) bookService.getBooks();
        List<BookDTO> booksDTO = books.stream().map(b -> bookMapper.convertToDTO(b)).toList();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", booksDTO))
                .message("All books retrieved!")
                .build()
        );
    }

    @GetMapping("/books/all/count")
    public ResponseEntity<CustomResponse> getAllBooksCount() {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", bookService.countBooks()))
                .message("Count retrieved!")
                .build()
        );
    }

    @GetMapping("/books/{id}/{firstName}/{lastName}/count")
    public ResponseEntity<CustomResponse> getBooksCountByAuthorName(@PathVariable Long id, @PathVariable(value = "firstName") String firstName, @PathVariable(value = "lastName") String lastName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", bookService.countBooksByAuthorNameAndAuthorID(id, firstName, lastName)))
                .message("Count retrieved!")
                .build()
        );
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<CustomResponse> getBookByID(@PathVariable Long id) {
        BookDTO bookDTO = bookMapper.convertToDTO(bookService.getBookByID(id));
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", bookDTO))
                .message("Book retrieved!")
                .build()
        );
    }

    @GetMapping(path = "/books/images/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getBookImage(@PathVariable("imageName") String imageName) throws IOException {
        return Files.readAllBytes(Path.of(IMAGE_PATH + imageName));
    }

    @DeleteMapping("/delete/book/{id}")
    public ResponseEntity<CustomResponse> deleteBookByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", bookService.deleteBookByID(id)))
                .message("Book deleted!")
                .build()
        );
    }

    @PostMapping("/save/book/{id}/{firstName}/{lastName}")
    public ResponseEntity<CustomResponse> saveBook(@PathVariable Long id, @PathVariable(value = "firstName") String firstName, @PathVariable(value = "lastName") String lastName, @RequestBody @Valid BookDTO bookDTO) {
        Book book = bookMapper.convertToEntity(bookDTO, id);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", bookMapper.convertToDTO(bookService.saveBook(book, firstName, lastName, id))))
                .message("Book saved!")
                .build()
        );
    }

    @PatchMapping("/update/book/{id}/{authorID}")
    public ResponseEntity<CustomResponse> updateBook(@PathVariable("id") Long id, @PathVariable("authorID") Long authorID, @RequestBody @Valid BookDTO bookDTO) {
        Book book = bookMapper.convertToEntity(bookDTO, authorID);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", bookMapper.convertToDTO(bookService.updateBook(id, book))))
                .message("Book updated!")
                .build()
        );
    }

    @PutMapping("/modify/book/{id}/{authorID}")
    public ResponseEntity<CustomResponse> modifyBook(@PathVariable("id") Long id, @PathVariable("authorID") Long authorID, @RequestBody @Valid BookDTO bookDTO) {
        Book book = bookMapper.convertToEntity(bookDTO, authorID);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", bookMapper.convertToDTO(bookService.modifyBook(id, book))))
                .message("Book updated!")
                .build()
        );
    }
}
