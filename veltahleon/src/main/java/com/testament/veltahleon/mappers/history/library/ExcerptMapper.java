package com.testament.veltahleon.mappers.history.library;

import com.testament.veltahleon.dto.history.library.ExcerptDTO;
import com.testament.veltahleon.model.entities.history.library.Excerpt;
import com.testament.veltahleon.services.ifc.history.library.BookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ExcerptMapper {

    @Autowired
    private BookService bookService;

    public ExcerptDTO convertToDTO(Excerpt excerpt) {
        return ExcerptDTO.builder()
                .id(excerpt.getId())
                .body(excerpt.getBody())
                .book(excerpt.getBook().getName())
                .build();
    }

    public Excerpt convertToEntity(ExcerptDTO excerptDTO) {
        Excerpt excerpt = new Excerpt();
        excerpt.setBody(excerptDTO.getBody());
        excerpt.setBook(bookService.getBookByBookName(excerptDTO.getBook()));
        return excerpt;
    }
}
