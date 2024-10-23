package com.testament.veltahleon.services.ifc.history;

import com.testament.veltahleon.model.entities.history.Language;

import java.util.Collection;

public interface LanguageService {

    Collection<Language> getLanguagesWithPagination(int pageNumber, int numberOfRecords);
    Collection<Language> getLanguages();
    Language getLanguageByID(Long id);
    Language getLanguageByName(String name);
    Boolean deleteLanguageByID(Long id);
    Boolean deleteAllLanguages(Collection<Language> languages);
    Language saveLanguage(Language language);
    Collection<Language> saveLanguages(Collection<Language> languages);
    Language updateLanguage(Long id, Language language);
    Language modifyLanguage(Long id, Language language);
    Boolean doesLanguageNameExist(String name);
    Long countLanguages();
}
