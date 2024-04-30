package com.testament.veltahleon.mappers.history.library;

import com.testament.veltahleon.dto.history.library.BookDTO;
import com.testament.veltahleon.model.entities.history.library.Book;
import com.testament.veltahleon.services.ifc.history.library.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookMapper {

    @Autowired
    private AuthorService authorService;

    public BookDTO convertToDTO(Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .name(book.getName())
                .authorFirstName(book.getAuthor().getFirstName())
                .authorLastName(book.getAuthor().getLastName())
                .build();
    }

    public Book convertToEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setName(bookDTO.getName());
        book.setAuthor(authorService.getAuthorByFirstName(bookDTO.getAuthorFirstName()));
        return book;
    }
}
