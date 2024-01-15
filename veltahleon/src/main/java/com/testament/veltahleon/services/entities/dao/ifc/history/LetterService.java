package com.testament.veltahleon.services.entities.dao.ifc.history;

import com.testament.veltahleon.model.entities.history.Letter;

import java.util.Collection;

public interface LetterService {

    Collection<Letter> getLetters();
    Letter getLetterByID(Long id);
    Boolean deleteLetterByID(Long id);
    Letter saveLetter(Letter letter);
    Letter updateLetter(Letter letter);
}
