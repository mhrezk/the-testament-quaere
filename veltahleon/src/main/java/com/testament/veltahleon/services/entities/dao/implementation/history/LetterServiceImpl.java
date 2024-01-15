package com.testament.veltahleon.services.entities.dao.implementation.history;

import com.testament.veltahleon.model.entities.history.Letter;
import com.testament.veltahleon.repositories.dao.ifc.history.LetterDAO;
import com.testament.veltahleon.services.entities.dao.ifc.history.LetterService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class LetterServiceImpl implements LetterService {

    @Autowired
    private LetterDAO letterDAO;

    @Override
    public Collection<Letter> getLetters() {
        return letterDAO.getLetters();
    }

    @Override
    public Letter getLetterByID(Long id) {
        return letterDAO.getLetterByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteLetterByID(Long id) {
        return letterDAO.deleteLetterByID(id);
    }

    @Override
    @Transactional
    public Letter saveLetter(Letter letter) {
        return letterDAO.saveLetter(letter);
    }

    @Override
    @Transactional
    public Letter updateLetter(Letter letter) {
        return letterDAO.updateLetter(letter);
    }
}
