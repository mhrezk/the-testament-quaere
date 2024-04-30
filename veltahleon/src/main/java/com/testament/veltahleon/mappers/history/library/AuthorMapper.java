package com.testament.veltahleon.mappers.history.library;

import com.testament.veltahleon.dto.history.library.AuthorDTO;
import com.testament.veltahleon.model.entities.history.library.Author;
import com.testament.veltahleon.model.entities.history.library.Book;
import com.testament.veltahleon.services.ifc.history.library.BookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthorMapper {

    @Autowired
    private BookService bookService;

    public AuthorDTO convertToDTO(Author author) {
        return AuthorDTO.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .books(author.getBooks().stream().map(Book::getName).toList())
                .build();
    }

    public Author convertToEntity(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setFirstName(authorDTO.getFirstName());
        author.setLastName(authorDTO.getLastName());
        author.setBooks(authorDTO.getBooks().stream().map(b -> bookService.getBookByBookName(b)).toList());
        return author;
    }
}
