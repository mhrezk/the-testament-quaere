package com.testament.veltahleon.rest.history;

import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.model.entities.history.Language;
import com.testament.veltahleon.services.ifc.history.LanguageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @GetMapping("/languages")
    public ResponseEntity<CustomResponse> getPaginatedLanguages(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Language> languages = (List<Language>) languageService.getLanguagesWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", languages))
                .message(languages.size() + " languages retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/languages/all")
    public ResponseEntity<CustomResponse> getAllLanguages() {
        List<Language> languages = (List<Language>) languageService.getLanguages();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", languages))
                .message("All languages retrieved!")
                .build()
        );
    }

    @GetMapping("/language/{id}")
    public ResponseEntity<CustomResponse> getLanguageByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", languageService.getLanguageByID(id)))
                .message("Language retrieved!")
                .build()
        );
    }

    @GetMapping("/language/name")
    public ResponseEntity<CustomResponse> getLanguagesByLanguageName(@RequestParam(value = "languageName") String languageName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", languageService.getLanguageByName(languageName)))
                .message("Language retrieved!")
                .build()
        );
    }

    @GetMapping(value = "/language/image/{fileName}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public byte[] getAlphabet(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get("src/main/java/com/testament/veltahleon/assets/images/languages/alphabet/" + fileName));
    }

    @PostMapping("/save/language")
    public ResponseEntity<CustomResponse> saveLanguage(@RequestBody @Valid Language language) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", languageService.saveLanguage(language)))
                .message("Language saved!")
                .build()
        );
    }

    @PostMapping("/save/languages")
    public ResponseEntity<CustomResponse> saveAllLanguages(@RequestBody Collection<Language> languages) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", languageService.saveLanguages(languages)))
                .message("Languages saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/language/{id}")
    public ResponseEntity<CustomResponse> deleteLanguageByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", languageService.deleteLanguageByID(id)))
                .message("Language deleted!")
                .build()
        );
    }

    @DeleteMapping("/delete/languages")
    public ResponseEntity<CustomResponse> deleteAllLanguages() {
        Collection<Language> languages = languageService.getLanguages();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", languageService.deleteAllLanguages(languages)))
                .message("Languages deleted!")
                .build()
        );
    }

    @PatchMapping("/update/language/{id}")
    public ResponseEntity<CustomResponse> updateLanguage(@PathVariable Long id, @RequestBody Language language) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", languageService.updateLanguage(id, language)))
                .message("Language updated!")
                .build()
        );
    }
}
