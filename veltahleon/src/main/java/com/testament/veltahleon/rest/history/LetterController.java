package com.testament.veltahleon.rest.history;

import com.testament.veltahleon.dto.history.LetterDTO;
import com.testament.veltahleon.mappers.history.LetterMapper;
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
import java.nio.file.Path;
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

    @Autowired
    private LetterMapper letterMapper;

    public final String imagePath = "src/main/resources/assets/images/letters/";

    @GetMapping("/letters")
    public ResponseEntity<CustomResponse> getPaginatedLetters(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Letter> letters = (List<Letter>) letterService.getLettersWithPagination((pageNumber -1), pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", letters))
                .message(letters.size() + " letters retrieved from page: " + pageNumber)
                .build()
        );
    }

    @GetMapping("/letters/language/{languageName}")
    public ResponseEntity<CustomResponse> getPaginatedLettersByLanguageName(@PathVariable(value = "languageName") String languageName, @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Letter> letters = (List<Letter>) letterService.getLettersWithPaginationByLanguageName(languageName, (pageNumber -1), pageSize);
        List<LetterDTO> lettersDTO = letters.stream().map(l -> letterMapper.convertToDTO(l)).toList();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", letters))
                .message(lettersDTO.size() + " letters retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/letters/all")
    public ResponseEntity<CustomResponse> getAllLetters() {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", letterService.countLetters()))
                .message("All letters retrieved!")
                .build()
        );
    }

    @GetMapping("/letters/{languageName}")
    public ResponseEntity<CustomResponse> getLettersByLanguageName(@PathVariable("languageName") String languageName) {
        List<Letter> letters = (List<Letter>) letterService.getLettersByLanguageName(languageName);
        List<LetterDTO> lettersDTO = letters.stream().map(l -> letterMapper.convertToDTO(l)).toList();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", lettersDTO))
                .message("Letters retrieved!")
                .build()
        );
    }

    @GetMapping("/letter/{id}")
    public ResponseEntity<CustomResponse> getLetterByID(@PathVariable Long id) {
        Letter letter = letterService.getLetterByID(id);
        LetterDTO letterDTO = letterMapper.convertToDTO(letter);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", letterDTO))
                .message("Letter retrieved!")
                .build()
        );
    }

    @GetMapping(path = "/letters/images/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getScriptImage(@PathVariable("imageName") String imageName) throws IOException {
        return Files.readAllBytes(Path.of(imagePath + imageName));
    }

    @PostMapping("/save/letter/{languageName}")
    public ResponseEntity<CustomResponse> saveLetter(@PathVariable(value = "languageName") String languageName, @RequestBody @Valid LetterDTO letterDTO) {
        Letter letter = letterMapper.convertToEntity(letterDTO);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", letterMapper.convertToDTO(letterService.saveLetter(letter, languageName))))
                .message("Letter saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/letter/{id}/{languageName}")
    public ResponseEntity<CustomResponse> deleteLetterByID(@PathVariable Long id, @PathVariable(value = "languageName") String languageName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", letterService.deleteLetterByID(id, languageName)))
                .message("Letter deleted!")
                .build()
        );
    }

    @PatchMapping("/update/letter/{id}")
    public ResponseEntity<CustomResponse> updateLetter(@PathVariable("id") Long id, @RequestBody @Valid LetterDTO letterDTO) {
        Letter letter = letterMapper.convertToEntity(letterDTO);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", letterMapper.convertToDTO(letterService.updateLetter(id, letter))))
                .message("Letter updated")
                .build()
        );
    }

    @PutMapping("/modify/letter/{id}")
    public ResponseEntity<CustomResponse> modifyLetter(@PathVariable("id") Long id, @RequestBody @Valid LetterDTO letterDTO) {
        Letter letter = letterMapper.convertToEntity(letterDTO);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", letterMapper.convertToDTO(letterService.modifyLetter(id, letter))))
                .message("Letter updated")
                .build()
        );
    }
}
