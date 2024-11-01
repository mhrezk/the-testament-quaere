package com.testament.veltahleon.mappers.history.library;

import com.testament.veltahleon.dto.history.library.AuthorDTO;
import com.testament.veltahleon.enumerations.Gender;
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
                .middleName(author.getMiddleName())
                .lastName(author.getLastName())
                .penName(author.getPenName())
                .birthYear(author.getBirthYear())
                .deathYear(author.getDeathYear())
                .birthYearAbbreviation(author.getBirthYearAbbreviation())
                .biography(author.getBiography())
                .imageURL(author.getImageURL())
                .gender(String.valueOf(author.getGender()))
                .build();
    }

    public Author convertToEntity(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setFirstName(authorDTO.getFirstName());
        author.setMiddleName(authorDTO.getMiddleName());
        author.setLastName(authorDTO.getLastName());
        author.setPenName(authorDTO.getPenName());
        author.setBirthYear(authorDTO.getBirthYear());
        author.setBirthYearAbbreviation(authorDTO.getBirthYearAbbreviation());
        author.setDeathYear(authorDTO.getDeathYear());
        author.setBiography(authorDTO.getBiography());
        author.setImageURL(authorDTO.getImageURL());
        author.setGender(Gender.valueOf(authorDTO.getGender()));
        return author;
    }
}
