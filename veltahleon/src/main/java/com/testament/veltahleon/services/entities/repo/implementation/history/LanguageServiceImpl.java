package com.testament.veltahleon.services.entities.repo.implementation.history;

import com.testament.veltahleon.exceptions.DataInsertionException;
import com.testament.veltahleon.model.entities.history.Language;
import com.testament.veltahleon.model.entities.history.Letter;
import com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.history.LanguageRepository;
import com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.history.LetterRepository;
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
@Transactional
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
    public Boolean deleteLanguageByID(Long id) {
        languageRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteAllLanguages(Collection<Language> languages) {
        languageRepository.deleteAll(languages);
        return Boolean.TRUE;
    }

    @Override
    public Language saveLanguage(Language language) {
        return languageRepository.save(language);
    }

    @Override
    public Collection<Language> saveLanguages(Collection<Language> languages) {
        return languageRepository.saveAll(languages);
    }

    @Override
    public Language updateLanguage(Long id, Language language) {
        Language newLanguage = languageRepository.findById(id).orElseThrow();

        if(language.getName() != null && newLanguage.getName() != language.getName()) {
            newLanguage.setName(language.getName());
        }

        if(language.getDescription() != null && newLanguage.getDescription() != language.getDescription()) {
            newLanguage.setDescription(language.getDescription());
        }

        if(language.getAlphabetURL() != null && newLanguage.getAlphabetURL() != language.getAlphabetURL()) {
            newLanguage.setAlphabetURL(language.getAlphabetURL());
        }

        return languageRepository.save(newLanguage);
    }

    //Helper Methods
    private String capitalizeName(String word) {
        String firstCharacter = word.toLowerCase().substring(0, 1).toUpperCase();
        return firstCharacter + word.substring(1);
    }

    private void validateLanguageEntry(Language language) {
        if(language.getName() == null || language.getName().isEmpty() || language.getName().isBlank()) {
            throw new DataInsertionException("Language name must be present!");
        }

        if(languageRepository.countByName(language.getName()) >= 1) {
            throw new DataInsertionException("Language name already exists! Duplicate entries are disallowed!");
        }
    }
}
