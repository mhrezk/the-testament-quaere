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
                .releaseDate(book.getReleaseDate())
                .releaseYearAbbreviation(book.getReleaseYearAbbreviation())
//                .authorFirstName(book.getAuthor().getFirstName())
//                .authorLastName(book.getAuthor().getLastName())
                .description(book.getDescription())
                .coverURL(book.getCoverURL())
                .build();
    }

    public Book convertToEntity(BookDTO bookDTO, Long authorID) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setName(bookDTO.getName());
        book.setDescription(bookDTO.getDescription());
        book.setReleaseDate(bookDTO.getReleaseDate());
        book.setReleaseYearAbbreviation(bookDTO.getReleaseYearAbbreviation());
        book.setCoverURL(bookDTO.getCoverURL());
        book.setAuthor(authorService.getAuthorByID(authorID));
        return book;
    }
}
