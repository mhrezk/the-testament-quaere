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
import java.nio.file.Path;
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

    public final String imagePath = "src/main/resources/assets/images/languages/";

    @GetMapping("/languages")
    public ResponseEntity<CustomResponse> getPaginatedLanguages(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Language> languages = (List<Language>) languageService.getLanguagesWithPagination((pageNumber - 1), pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", languages))
                .message(languages.size() + " languages retrieved from page: " + pageNumber)
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

    @GetMapping("/languages/all/count")
    public ResponseEntity<CustomResponse> getAllLanguagesCount() {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", languageService.countLanguages()))
                .message("Count retrieved!")
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
    public ResponseEntity<CustomResponse> getLanguageByName(@RequestParam(value = "languageName") String languageName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", languageService.getLanguageByName(languageName)))
                .message("Language retrieved!")
                .build()
        );
    }

    @GetMapping("/language/{languageName}/exist")
    public ResponseEntity<CustomResponse> getLanguageNameExistence(@PathVariable(value = "languageName") String languageName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", languageService.doesLanguageNameExist(languageName)))
                .message("Race name exists!")
                .build()
        );
    }

    @GetMapping(path = "/languages/images/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getLinguisticImage(@PathVariable("imageName") String imageName) throws IOException {
        return Files.readAllBytes(Path.of(imagePath + imageName));
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

    @PutMapping("/modify/language/{id}")
    public ResponseEntity<CustomResponse> modifyLanguage(@PathVariable Long id, @RequestBody Language language) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", languageService.modifyLanguage(id, language)))
                .message("Language updated!")
                .build()
        );
    }
}
