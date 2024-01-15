package com.testament.veltahleon.services.dao.implementation.history.library;

import com.testament.veltahleon.model.entities.history.library.Excerpt;
import com.testament.veltahleon.repositories.dao.ifc.history.library.ExcerptDAO;
import com.testament.veltahleon.services.dao.ifc.history.library.ExcerptService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ExcerptServiceImpl implements ExcerptService {

    @Autowired
    private ExcerptDAO excerptDAO;

    @Override
    public Collection<Excerpt> getExcerpts() {
        return excerptDAO.getExcerpts();
    }

    @Override
    public Excerpt getExcerptByID(Long id) {
        return excerptDAO.getExcerptByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteExcerptByID(Long id) {
        return excerptDAO.deleteExcerptByID(id);
    }

    @Override
    @Transactional
    public Excerpt saveExcerpt(Excerpt excerpt) {
        return excerptDAO.saveExcerpt(excerpt);
    }

    @Override
    @Transactional
    public Excerpt updateExcerpt(Excerpt excerpt) {
        return excerptDAO.updateExcerpt(excerpt);
    }
}
