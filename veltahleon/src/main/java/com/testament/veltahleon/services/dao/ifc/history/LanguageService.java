package com.testament.veltahleon.services.dao.ifc.history;

import com.testament.veltahleon.model.entities.history.Language;

import java.util.Collection;

public interface LanguageService {

    Collection<Language> getLanguages();
    Language getLanguageByID(Long id);
    Boolean deleteLanguageByID(Long id);
    Language saveLanguage(Language language);
    Language updateLanguage(Language language);
}
