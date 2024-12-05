package com.testament.veltahleon.mappers.history;

import com.testament.veltahleon.dto.history.VocabularyDTO;
import com.testament.veltahleon.model.entities.history.Vocabulary;
import com.testament.veltahleon.services.ifc.history.LanguageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VocabularyMapper {

    @Autowired
    private LanguageService languageService;

    public VocabularyDTO convertToDTO(Vocabulary vocabulary) {
        return VocabularyDTO.builder()
                .id(vocabulary.getId())
                .word(vocabulary.getWord())
                .pronunciation(vocabulary.getPronunciation())
                .definition(vocabulary.getDefinition())
                .language(vocabulary.getLanguage().getName())
                .build();
    }

    public Vocabulary convertToEntity(VocabularyDTO vocabularyDTO) {
        return Vocabulary.builder()
                .word(vocabularyDTO.getWord())
                .pronunciation(vocabularyDTO.getPronunciation())
                .definition(vocabularyDTO.getDefinition())
                .language(languageService.getLanguageByName(vocabularyDTO.getLanguage()))
                .build();
    }
}
