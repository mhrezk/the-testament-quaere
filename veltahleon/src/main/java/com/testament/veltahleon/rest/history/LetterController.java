package com.testament.veltahleon.rest.history;

import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.model.entities.history.Letter;
import com.testament.veltahleon.services.ifc.history.LetterService;
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
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class LetterController {

    @Autowired
    private LetterService letterService;

    @GetMapping("/letters")
    public ResponseEntity<CustomResponse> getPaginatedLetters(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Letter> letters = (List<Letter>) letterService.getLettersWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", letters))
                .message(letters.size() + " letters retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/letters/all")
    public ResponseEntity<CustomResponse> getAllLetters() {
        List<Letter> letters = (List<Letter>) letterService.getLetters();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", letters))
                .message("All letters retrieved!")
                .build()
        );
    }

    @GetMapping("/letters/{languageName}")
    public ResponseEntity<CustomResponse> getLetterByLanguageName(@PathVariable("languageName") String languageName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", letterService.getLettersByLanguageName(languageName)))
                .message("Letter retrieved!")
                .build()
        );
    }

    @GetMapping("/letter/{id}")
    public ResponseEntity<CustomResponse> getLetterByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", letterService.getLetterByID(id)))
                .message("Letter retrieved!")
                .build()
        );
    }

    @GetMapping(value = "/letter/image/{fileName}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public byte[] getLetterScript(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get("src/main/java/com/testament/veltahleon/assets/images/languages/letters/" + fileName));
    }

    @PostMapping("/save/letter")
    public ResponseEntity<CustomResponse> saveLetter(@RequestBody @Valid Letter letter) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", letterService.saveLetter(letter)))
                .message("Letter saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/letter/{id}")
    public ResponseEntity<CustomResponse> deleteLetterByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", letterService.deleteLetterByID(id)))
                .message("Letter deleted!")
                .build()
        );
    }

    @PatchMapping("/update/letter/{id}")
    public ResponseEntity<CustomResponse> updateLetter(@PathVariable("id") Long id, Letter letter) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", letterService.updateLetter(id, letter)))
                .message("Letter updated")
                .build()
        );
    }
}
