package com.testament.veltahleon.services.entities.repo.implementation.history;

import com.testament.veltahleon.exceptions.DataInsertionException;
import com.testament.veltahleon.model.entities.calendar.Day;
import com.testament.veltahleon.model.entities.history.Language;
import com.testament.veltahleon.model.entities.history.Letter;
import com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.history.LanguageRepository;
import com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.history.LetterRepository;
import com.testament.veltahleon.services.entities.repo.ifc.history.LetterService;
import jakarta.persistence.EntityManager;
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
public class LetterServiceImpl implements LetterService {

    @Autowired
    private final EntityManager entityManager;

    @Autowired
    private final LetterRepository letterRepository;

//    @Autowired
//    private final LanguageRepository languageRepository;

    @Override
    public Collection<Letter> getLettersWithPagination(int pageNumber, int numberOfRecords) {
        return letterRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Letter> getLetters() {
        return letterRepository.findAll();
    }

//    @Override
//    public Collection<Letter> getLettersByLanguageName(String languageName) {
//        return letterRepository.findByLanguageName(languageName);
//    }

    @Override
    public Letter getLetterByID(Long id) {
        return letterRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Boolean deleteLetterByID(Long id) {
        letterRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Letter saveLetter(Letter letter) {
        validateLetterEntry(letter);
        String properName = capitalizeName(letter.getName());
        for(Language l : letter.getLanguages()) {
            String languageProperName = capitalizeName(l.getName());
            l.addLetter(letter);
            l.setName(languageProperName);
        }
        letter.setName(properName);
//        String languageProperName = capitalizeName(letter.getLanguage().getName());
//        letter.setName(properName);
//        letter.getLanguage().setName(languageProperName);
//        letter.getLanguage().addLetter(letter);
        return letterRepository.save(letter);
    }

    @Override
    @Transactional
    public Letter updateLetter(Letter letter) {
        return null;
    }

    //Helper Methods
    private String capitalizeName(String word) {
        String firstCharacter = word.toLowerCase().substring(0, 1).toUpperCase();
        return firstCharacter + word.substring(1);
    }

    private void validateLetterEntry(Letter letter) {
        if(letter.getName() == null || letter.getName().isEmpty() || letter.getName().isBlank()) {
            throw new DataInsertionException("Letter name must be present!");
        }

//        if(letter.getLanguage().getName() == null || letter.getLanguage().getName().isEmpty() || letter.getLanguage().getName().isBlank()) {
//            throw new DataInsertionException("Language name must be present!");
//        }
//
//        if(letterRepository.countByName(letter.getName()) >= 1) {
//            throw new DataInsertionException("Letter name already exists! Duplicate entries are disallowed!");
//        }
//
//        if(languageRepository.countByName(letter.getLanguage().getName()) >= 1) {
//            Language language = languageRepository.findByName(letter.getLanguage().getName());
//            letter.getLanguage().setId(language.getId());
//            letter.setLanguage(language);
//        }
    }
}
