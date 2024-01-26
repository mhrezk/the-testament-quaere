package com.testament.veltahleon.services.entities.repo.ifc.history;

import com.testament.veltahleon.model.entities.history.Language;
import com.testament.veltahleon.model.entities.history.Letter;

import java.util.Collection;

public interface LetterService {

    Collection<Letter> getLettersWithPagination(int pageNumber, int numberOfRecords);
    Collection<Letter> getLetters();
    //Collection<Letter> getLettersByLanguageName(String languageName);
    Letter getLetterByID(Long id);
    Boolean deleteLetterByID(Long id);
    Letter saveLetter(Letter letter);
    Letter updateLetter(Letter letter);
}
