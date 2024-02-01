package com.testament.veltahleon.services.entities.repo.ifc.history.library;

import com.testament.veltahleon.model.entities.history.library.Excerpt;

import java.util.Collection;

public interface ExcerptService {

    Collection<Excerpt> getExcerptsWithPagination(int pageNumber, int numberOfRecords);
    Collection<Excerpt> getExcerpts();
    Collection<Excerpt> getExcerptsByBookName(String name);
    Excerpt getExcerptByID(Long id);
    Boolean deleteExcerptByID(Long id);
    Excerpt saveExcerpt(Excerpt excerpt);
    Excerpt updateExcerpt(Long id, Excerpt excerpt);
}
