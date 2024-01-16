package com.testament.veltahleon.repositories.repo.ifc.history;

import com.testament.veltahleon.model.entities.history.Letter;

import java.util.Collection;

public interface LetterRepository {

    Collection<Letter> getLetters();
    Letter getLetterByID(Long id);
    Boolean deleteLetterByID(Long id);
    Letter saveLetter(Letter letter);
    Letter updateLetter(Letter letter);
}
