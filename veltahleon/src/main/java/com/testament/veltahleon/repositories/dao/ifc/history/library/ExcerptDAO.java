package com.testament.veltahleon.repositories.dao.ifc.history.library;

import com.testament.veltahleon.model.entities.history.library.Excerpt;

import java.util.Collection;

public interface ExcerptDAO {

    Collection<Excerpt> getExcerpts();
    Excerpt getExcerptByID(Long id);
    Boolean deleteExcerptByID(Long id);
    Excerpt saveExcerpt(Excerpt excerpt);
    Excerpt updateExcerpt(Excerpt excerpt);
}
