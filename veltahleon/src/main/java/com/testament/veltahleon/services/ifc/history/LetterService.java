package com.testament.veltahleon.services.ifc.history;

import com.testament.veltahleon.model.entities.history.Language;
import com.testament.veltahleon.model.entities.history.Letter;

import java.util.Collection;

public interface LetterService {

    Collection<Letter> getLettersWithPagination(int pageNumber, int numberOfRecords);
    Collection<Letter> getLettersWithPaginationByLanguageName(String name, int pageNumber, int numberOfRecords);
    Collection<Letter> getLetters();
    Collection<Letter> getLettersByLanguageName(String languageName);
    Letter getLetterByID(Long id);
    Letter getLetterByName(String name);
    Boolean deleteLetterByID(Long id, String name);
    Letter saveLetter(Letter letter, String name);
    Letter updateLetter(Long id, Letter letter);
    Letter modifyLetter(Long id, Letter letter);
    Long countLetters();
    Long countLettersByLanguageName(String languageName);
}
