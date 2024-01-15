package com.testament.veltahleon.services.dao.ifc.history.library;

import com.testament.veltahleon.model.entities.history.library.Chapter;

import java.util.Collection;

public interface ChapterService {

    Collection<Chapter> getChapters();
    Chapter getChapterByID(Long id);
    Boolean deleteChapterByID(Long id);
    Chapter saveChapter(Chapter chapter);
    Chapter updateChapter(Chapter chapter);
}
