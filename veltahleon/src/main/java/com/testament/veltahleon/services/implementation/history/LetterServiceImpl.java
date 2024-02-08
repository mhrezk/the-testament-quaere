package com.testament.veltahleon.services.implementation.history;

import com.testament.veltahleon.exceptions.DataInsertionException;
import com.testament.veltahleon.model.entities.history.Letter;
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

    @Override
    public Collection<Letter> getLettersWithPagination(int pageNumber, int numberOfRecords) {
        return letterRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Letter> getLetters() {
        return letterRepository.findAll();
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

        if(letter.getName() != null && newLetter.getName() != letter.getName()) {
            newLetter.setName(letter.getName());
        }

        if(letter.getScriptURL() != null && newLetter.getScriptURL() != letter.getScriptURL()) {
            newLetter.setScriptURL(letter.getScriptURL());
        }

        return letterRepository.save(newLetter);
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
