package com.testament.veltahleon.repositories.repo.ifc.history.library;

import com.testament.veltahleon.model.entities.history.library.Chapter;

import java.util.Collection;

public interface ChapterRepository {

    Collection<Chapter> getChapters();
    Chapter getChapterByID(Long id);
    Boolean deleteChapterByID(Long id);
    Chapter saveChapter(Chapter chapter);
    Chapter updateChapter(Chapter chapter);
}
