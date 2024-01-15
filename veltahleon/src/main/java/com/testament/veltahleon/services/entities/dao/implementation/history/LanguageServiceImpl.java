package com.testament.veltahleon.services.entities.dao.implementation.history;

import com.testament.veltahleon.model.entities.history.Language;
import com.testament.veltahleon.repositories.dao.ifc.history.LanguageDAO;
import com.testament.veltahleon.services.entities.dao.ifc.history.LanguageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    private LanguageDAO languageDAO;

    @Override
    public Collection<Language> getLanguages() {
        return languageDAO.getLanguages();
    }

    @Override
    public Language getLanguageByID(Long id) {
        return languageDAO.getLanguageByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteLanguageByID(Long id) {
        return languageDAO.deleteLanguageByID(id);
    }

    @Override
    @Transactional
    public Language saveLanguage(Language language) {
        return languageDAO.saveLanguage(language);
    }

    @Override
    @Transactional
    public Language updateLanguage(Language language) {
        return languageDAO.updateLanguage(language);
    }
}
