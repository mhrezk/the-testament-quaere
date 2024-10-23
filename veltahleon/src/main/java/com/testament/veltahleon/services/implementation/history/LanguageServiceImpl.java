package com.testament.veltahleon.services.implementation.history;

import com.testament.veltahleon.exceptions.DataInsertionException;
import com.testament.veltahleon.model.entities.history.Language;
import com.testament.veltahleon.model.entities.history.Letter;
import com.testament.veltahleon.repositories.history.LanguageRepository;
import com.testament.veltahleon.repositories.history.LetterRepository;
import com.testament.veltahleon.services.ifc.history.LanguageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    private final LanguageRepository languageRepository;

    @Autowired
    private final LetterRepository letterRepository;

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
        return languageRepository.findById(id).orElseThrow();
    }

    @Override
    public Language getLanguageByName(String name) {
        return languageRepository.findByName(name);
    }

    @Override
    public Boolean deleteLanguageByID(Long id) {
        List<Letter> letters = (List<Letter>) letterRepository.findByLanguage_Name(languageRepository.findById(id).orElseThrow().getName());
        letterRepository.deleteAllById(letters.stream().map(Letter::getId).toList());
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
        if(languageRepository.countByName(language.getName().toLowerCase()) >= 1) {
            throw new DataInsertionException("Language name");
        }
        language.setName(language.getName().toUpperCase());
        language.setAlphabetSize(0);
        language.setAlphabetURL(defaultAlphabetURL("arabic.png"));
        return languageRepository.save(language);
    }

    private String defaultAlphabetURL(String imageName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/history/languages/images/" + imageName).toUriString();
    }

    @Override
    public Collection<Language> saveLanguages(Collection<Language> languages) {
        return languageRepository.saveAll(languages);
    }

    @Override
    public Language updateLanguage(Long id, Language language) {
        Language newLanguage = languageRepository.findById(id).orElseThrow();

        if(language.getName() != null && newLanguage.getName() != language.getName()) {
            newLanguage.setName(language.getName().toUpperCase());
        }

        if(language.getDescription() != null && newLanguage.getDescription() != language.getDescription()) {
            newLanguage.setDescription(language.getDescription());
        }

        if(language.getAlphabetURL() != null && newLanguage.getAlphabetURL() != language.getAlphabetURL()) {
            newLanguage.setAlphabetURL(language.getAlphabetURL());
        }

        return languageRepository.save(newLanguage);
    }

    @Override
    public Language modifyLanguage(Long id, Language language) {
        Language newLanguage = languageRepository.findById(id).orElseThrow();
        newLanguage.setName(language.getName().toUpperCase());
        newLanguage.setDescription(language.getDescription());
        newLanguage.setAlphabetURL(language.getAlphabetURL());
        return languageRepository.save(newLanguage);
    }

    @Override
    public Boolean doesLanguageNameExist(String name) {
        return languageRepository.existsLanguageByName(name);
    }

    @Override
    public Long countLanguages() {
        return languageRepository.count();
    }
}
