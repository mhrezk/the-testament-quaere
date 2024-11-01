package com.testament.veltahleon.services.implementation.history.library;

import com.testament.veltahleon.model.entities.history.library.Chapter;
import com.testament.veltahleon.repositories.history.library.BookRepository;
import com.testament.veltahleon.repositories.history.library.ChapterRepository;
import com.testament.veltahleon.services.ifc.history.library.ChapterService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Collection<Chapter> getChaptersWithPagination(int pageNumber, int numberOfRecords) {
        return chapterRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Chapter> getChapters() {
        return chapterRepository.findAll();
    }

    @Override
    public Collection<Chapter> getChaptersByBookName(String name) {
        return chapterRepository.findByBook_Name(name);
    }

    @Override
    public Collection<Chapter> getPaginatedChaptersByBookName(String name, int pageNumber, int numberOfRecords) {
        return chapterRepository.findByBook_Name(name, PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public long getNumberOfChaptersPerBook(String bookName) {
        return chapterRepository.countByBook_Name(bookName);
    }

    @Override
    public Chapter getChapterByID(Long id) {
        return chapterRepository.findById(id).orElseThrow();
    }

    @Override
    public Boolean deleteChapterByID(Long id) {
        chapterRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Chapter saveChapter(Chapter chapter, String bookName) {
        chapter.setBook(bookRepository.findByName(bookName));
        return chapterRepository.save(chapter);
    }

    @Override
    public Chapter updateChapter(Long id, Chapter chapter) {
        Chapter newChapter = chapterRepository.findById(id).orElseThrow();

        if(chapter.getText() != null && newChapter.getText() != chapter.getText()) {
            newChapter.setText(chapter.getText());
        }

        if(chapter.getName() != null && newChapter.getName() != chapter.getName()) {
            newChapter.setName(chapter.getName());
        }

        if(chapter.getChapterNumber() != null && newChapter.getChapterNumber() != chapter.getChapterNumber()) {
            newChapter.setChapterNumber(chapter.getChapterNumber());
        }

//        if(chapter.getBook() != null && newChapter.getBook() != chapter.getBook()) {
//            newChapter.setBook(chapter.getBook());
//        }

        return chapterRepository.save(newChapter);
    }

    @Override
    public Chapter modifyChapter(Long id, Chapter chapter) {
        Chapter newChapter = chapterRepository.findById(id).orElseThrow();
        newChapter.setName(chapter.getName());
        newChapter.setText(chapter.getText());
        newChapter.setChapterNumber(chapter.getChapterNumber());
        return null;
    }
}
