package com.testament.veltahleon.services.entities.repo.implementation.history;

import com.testament.veltahleon.model.entities.history.Language;
import com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.history.LanguageRepository;
import com.testament.veltahleon.services.entities.repo.ifc.history.LanguageService;
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
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    private final LanguageRepository languageRepository;

    @Override
    public Collection<Language> getLanguagesWithPagination(int pageNumber, int numberOfRecords) {
        return languageRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Language> getLanguages() {
        return languageRepository.findAll();
    }

    @Override
    public Language getLanguageByID(Long id) {
        return languageRepository.findById(id).get();
    }

    @Override
    public Language getLanguageByName(String name) {
        return languageRepository.findByName(name);
    }

    @Override
    @Transactional
    public Boolean deleteLanguageByID(Long id) {
        languageRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Language saveLanguage(Language language) {
        return languageRepository.save(language);
    }

    @Override
    @Transactional
    public Language updateLanguage(Language language) {
        return null;
    }
}
