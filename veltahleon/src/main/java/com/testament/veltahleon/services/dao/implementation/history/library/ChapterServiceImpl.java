package com.testament.veltahleon.services.dao.implementation.history.library;

import com.testament.veltahleon.model.entities.history.library.Chapter;
import com.testament.veltahleon.repositories.dao.ifc.history.library.ChapterDAO;
import com.testament.veltahleon.services.dao.ifc.history.library.ChapterService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterDAO chapterDAO;

    @Override
    public Collection<Chapter> getChapters() {
        return chapterDAO.getChapters();
    }

    @Override
    public Chapter getChapterByID(Long id) {
        return chapterDAO.getChapterByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteChapterByID(Long id) {
        return chapterDAO.deleteChapterByID(id);
    }

    @Override
    @Transactional
    public Chapter saveChapter(Chapter chapter) {
        return chapterDAO.saveChapter(chapter);
    }

    @Override
    @Transactional
    public Chapter updateChapter(Chapter chapter) {
        return chapterDAO.updateChapter(chapter);
    }
}
