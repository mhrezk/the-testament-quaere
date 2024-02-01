package com.testament.veltahleon.services.entities.repo.implementation.history.library;

import com.testament.veltahleon.model.entities.history.library.Excerpt;
import com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.history.library.ExcerptRepository;
import com.testament.veltahleon.services.entities.repo.ifc.history.library.ExcerptService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ExcerptServiceImpl implements ExcerptService {

    @Autowired
    private ExcerptRepository excerptRepository;

    @Override
    public Collection<Excerpt> getExcerptsWithPagination(int pageNumber, int numberOfRecords) {
        return excerptRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Excerpt> getExcerpts() {
        return excerptRepository.findAll();
    }

    @Override
    public Collection<Excerpt> getExcerptsByBookName(String name) {
        return excerptRepository.findByBook_name(name);
    }

    @Override
    public Excerpt getExcerptByID(Long id) {
        return excerptRepository.findById(id).orElseThrow();
    }

    @Override
    public Boolean deleteExcerptByID(Long id) {
        excerptRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Excerpt saveExcerpt(Excerpt excerpt) {
        return excerptRepository.save(excerpt);
    }

    @Override
    public Excerpt updateExcerpt(Long id, Excerpt excerpt) {
        Excerpt newExcerpt = excerptRepository.findById(id).orElseThrow();

        if(excerpt.getBody() != null && newExcerpt.getBody() != excerpt.getBody()) {
            newExcerpt.setBody(excerpt.getBody());
        }

        if(excerpt.getBook() != null && newExcerpt.getBook() != excerpt.getBook()) {
            newExcerpt.setBook(excerpt.getBook());
        }

        return excerptRepository.save(newExcerpt);
    }
}
