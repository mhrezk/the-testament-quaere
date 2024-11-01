package com.testament.veltahleon.services.ifc.history.library;

import com.testament.veltahleon.model.entities.history.library.Chapter;

import java.util.Collection;

public interface ChapterService {

    Collection<Chapter> getChaptersWithPagination(int pageNumber, int numberOfRecords);
    Collection<Chapter> getChapters();
    Collection<Chapter> getChaptersByBookName(String name);
    Collection<Chapter> getPaginatedChaptersByBookName(String name, int pageNumber, int numberOfRecords);
    long getNumberOfChaptersPerBook(String bookName);
    Boolean deleteChapterByID(Long id);
    Chapter getChapterByID(Long id);
    Chapter saveChapter(Chapter chapter, String bookName);
    Chapter updateChapter(Long id, Chapter chapter);
    Chapter modifyChapter(Long id, Chapter chapter);
}
