package com.testament.veltahleon.mappers.history.library;

import com.testament.veltahleon.dto.history.library.ChapterDTO;
import com.testament.veltahleon.model.entities.history.library.Chapter;
import com.testament.veltahleon.services.ifc.history.library.BookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ChapterMapper {

    @Autowired
    private BookService bookService;

    public ChapterDTO convertToDTO(Chapter chapter) {
        return ChapterDTO.builder()
                .id(chapter.getId())
                .name(chapter.getName())
                .chapterNumber(chapter.getChapterNumber())
                .book(chapter.getBook().getName())
                .text(chapter.getText())
                .build();
    }

    public Chapter convertToEntity(ChapterDTO chapterDTO) {
        Chapter chapter = new Chapter();
        chapter.setName(chapterDTO.getName());
        chapter.setText(chapterDTO.getText());
        chapter.setChapterNumber(chapterDTO.getChapterNumber());
        chapter.setBook(bookService.getBookByBookName(chapterDTO.getBook()));
        return chapter;
    }
}
