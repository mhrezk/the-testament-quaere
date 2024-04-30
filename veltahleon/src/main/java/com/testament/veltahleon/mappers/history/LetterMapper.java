package com.testament.veltahleon.mappers.history;

import com.testament.veltahleon.dto.history.LetterDTO;
import com.testament.veltahleon.model.entities.history.Letter;
import com.testament.veltahleon.services.ifc.history.LanguageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LetterMapper {

    @Autowired
    private LanguageService languageService;

    public LetterDTO convertToDTO(Letter letter) {
        return LetterDTO.builder()
                .id(letter.getId())
                .name(letter.getName())
                .ipa(letter.getIpa())
                .language(letter.getLanguage().getName())
                .scriptURL(letter.getScriptURL())
                .build();
    }

    public Letter convertToEntity(LetterDTO letterDTO) {
        Letter letter = new Letter();
        letter.setName(letterDTO.getName());
        letter.setIpa(letterDTO.getIpa());
        letter.setScriptURL(letterDTO.getScriptURL());
        letter.setLanguage(languageService.getLanguageByName(letterDTO.getLanguage()));
        return letter;
    }
}
