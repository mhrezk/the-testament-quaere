package com.testament.veltahleon.services.ifc.history.library;

import com.testament.veltahleon.model.entities.history.library.Chapter;

import java.util.Collection;

public interface ChapterService {

    Collection<Chapter> getChaptersWithPagination(int pageNumber, int numberOfRecords);
    Collection<Chapter> getChapters();
    Collection<Chapter> getChaptersByBookName(String name);
    long getNumberOfChaptersPerBook(String bookName);
    Chapter getChapterByID(Long id);
    Boolean deleteChapterByID(Long id);
    Chapter saveChapter(Chapter chapter);
    Chapter updateChapter(Long id, Chapter chapter);
}
