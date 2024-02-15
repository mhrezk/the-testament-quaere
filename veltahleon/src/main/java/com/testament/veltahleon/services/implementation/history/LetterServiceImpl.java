package com.testament.veltahleon.services.implementation.history;

import com.testament.veltahleon.exceptions.DataInsertionException;
import com.testament.veltahleon.model.entities.history.Language;
import com.testament.veltahleon.model.entities.history.Letter;
import com.testament.veltahleon.repositories.history.LanguageRepository;
import com.testament.veltahleon.repositories.history.LetterRepository;
import com.testament.veltahleon.services.ifc.history.LetterService;
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
public class LetterServiceImpl implements LetterService {

    @Autowired
    private final LetterRepository letterRepository;
    @Autowired
    private final LanguageRepository languageRepository;

    @Override
    public Collection<Letter> getLettersWithPagination(int pageNumber, int numberOfRecords) {
        return letterRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Letter> getLetters() {
        return letterRepository.findAll();
    }

    @Override
    public Collection<Letter> getLettersByLanguageName(String languageName) {
        return letterRepository.findByLanguage_Name(languageName);
    }

    @Override
    public Letter getLetterByID(Long id) {
        return letterRepository.findById(id).orElseThrow();
    }

    @Override
    public Boolean deleteLetterByID(Long id) {
        letterRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Letter saveLetter(Letter letter) {
        return letterRepository.save(letter);
    }

    @Override
    public Letter updateLetter(Long id, Letter letter) {
        Letter newLetter = letterRepository.findById(id).orElseThrow();

        if((letter.getLanguage() != null && newLetter.getLanguage() != letter.getLanguage())) {
            newLetter.setLanguage(checkLanguageForUpdate(letter.getLanguage(), newLetter.getLanguage()));
        }

        if(letter.getScriptURL() != null && newLetter.getScriptURL() != letter.getScriptURL()) {
            newLetter.setScriptURL(letter.getScriptURL());
        }

        return letterRepository.save(newLetter);
    }

    //Helper Methods
    private Language checkLanguageForUpdate(Language language, Language newLanguage) {
        if(language.getName() != null && newLanguage.getName() != language.getName()) {
            newLanguage.setName(language.getName());
        }

        if(language.getDescription() != null && newLanguage.getDescription() != language.getDescription()) {
            newLanguage.setDescription(language.getDescription());
        }

        if(language.getAlphabetURL() != null && newLanguage.getAlphabetURL() != language.getAlphabetURL()) {
            newLanguage.setAlphabetURL(language.getAlphabetURL());
        }
        return newLanguage;
    }
}
