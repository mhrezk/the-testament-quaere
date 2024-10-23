package com.testament.veltahleon.services.implementation.history;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class LetterServiceImpl implements LetterService {

    @Autowired
    private final LetterRepository letterRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Override
    public Collection<Letter> getLettersWithPagination(int pageNumber, int numberOfRecords) {
        return letterRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Letter> getLettersWithPaginationByLanguageName(String name, int pageNumber, int numberOfRecords) {
        return letterRepository.findByLanguage_Name(name, PageRequest.of(pageNumber, numberOfRecords)).toList();
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
    public Letter getLetterByName(String name) {
        return letterRepository.findByName(name);
    }

    @Override
    public Boolean deleteLetterByID(Long id, String name) {
        Language language = languageRepository.findByName(name);
        languageRepository.updateAlphabetSize(language.getAlphabetSize() - 1, language.getId());
        letterRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Letter saveLetter(Letter letter, String name) {
        Language language = languageRepository.findByName(name);
        letter.setLanguage(languageRepository.findByName(name));
        letter.setName(letter.getName().toUpperCase());
        letter.setScriptURL(defaultScriptURL("quran.png"));
        languageRepository.updateAlphabetSize(language.getAlphabetSize() + 1, language.getId());
        return letterRepository.save(letter);
    }

    private String defaultScriptURL(String imageName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/history/letters/images/" + imageName).toUriString();
    }

    @Override
    public Letter updateLetter(Long id, Letter letter) {
        Letter newLetter = letterRepository.findById(id).orElseThrow();

        if(letter.getName() != null && newLetter.getName() != letter.getName()) {
            newLetter.setName(letter.getName().toUpperCase());
        }

        if(letter.getIpa() != null && newLetter.getIpa() != letter.getIpa()) {
            newLetter.setIpa(letter.getIpa());
        }

        if((letter.getLanguage() != null && newLetter.getLanguage() != letter.getLanguage())) {
            newLetter.setLanguage(letter.getLanguage());
        }

        if(letter.getScriptURL() != null && newLetter.getScriptURL() != letter.getScriptURL()) {
            newLetter.setScriptURL(letter.getScriptURL());
        }

        if(letter.getDescription() != null && newLetter.getDescription() != letter.getDescription()) {
            newLetter.setDescription(letter.getDescription());
        }

        return letterRepository.save(newLetter);
    }

    @Override
    public Letter modifyLetter(Long id, Letter letter) {
        Letter newLetter = letterRepository.findById(id).orElseThrow();
        System.out.println(letter.getName());
        newLetter.setName(letter.getName().toUpperCase());
        newLetter.setScriptURL(letter.getScriptURL());
        newLetter.setIpa(letter.getIpa());
        newLetter.setLanguage(letter.getLanguage());
        newLetter.setDescription(letter.getDescription());
        return letterRepository.save(newLetter);
    }

    @Override
    public Long countLetters() {
        return letterRepository.count();
    }

    @Override
    public Long countLettersByLanguageName(String languageName) {
        return letterRepository.countByLanguage_Name(languageName);
    }

    //Helper Methods
//    private Language checkLanguageForUpdate(Language language, Language newLanguage) {
//        if(language.getName() != null && newLanguage.getName() != language.getName()) {
//            newLanguage.setName(language.getName());
//        }
//
//        if(language.getDescription() != null && newLanguage.getDescription() != language.getDescription()) {
//            newLanguage.setDescription(language.getDescription());
//        }
//
//        if(language.getAlphabetURL() != null && newLanguage.getAlphabetURL() != language.getAlphabetURL()) {
//            newLanguage.setAlphabetURL(language.getAlphabetURL());
//        }
//        return newLanguage;
//    }
}
