package com.testament.veltahleon.services.entities.repo.ifc.history;

import com.testament.veltahleon.model.entities.history.Language;

import java.util.Collection;

public interface LanguageService {

    Collection<Language> getLanguagesWithPagination(int pageNumber, int numberOfRecords);
    Collection<Language> getLanguages();
    Language getLanguageByID(Long id);
    Language getLanguageByName(String name);
    Boolean deleteLanguageByID(Long id);
    Language saveLanguage(Language language);
    Language updateLanguage(Language language);
}
