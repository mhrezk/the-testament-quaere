package com.testament.veltahleon.rest.society;

import com.testament.veltahleon.model.entities.society.Title;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.society.TitleService;
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
@RequestMapping("/society")
@RequiredArgsConstructor
public class TitleController {

    @Autowired
    private TitleService titleService;

    @GetMapping("/titles/all")
    public ResponseEntity<CustomResponse> getAllTitles() {
        List<Title> titles = (List<Title>) titleService.getTitles();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", titles))
                .message("All Titles retrieved!")
                .build()
        );
    }

    @GetMapping("/title/{id}")
    public ResponseEntity<CustomResponse> getTitleByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", titleService.getTitleByID(id)))
                .message("Title retrieved!")
                .build()
        );
    }

    @PostMapping("/save/title")
    public ResponseEntity<CustomResponse> saveTitle(@RequestBody @Valid Title title) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", titleService.saveTitle(title)))
                .message("Title saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/title/{id}")
    public ResponseEntity<CustomResponse> deleteTitleByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", titleService.deleteTitleByID(id)))
                .message("Title deleted!")
                .build()
        );
    }

    @PatchMapping("/update/title/{id}")
    public ResponseEntity<CustomResponse> updateTitle(@PathVariable Long id, @RequestBody Title title) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", titleService.updateTitle(id, title)))
                .message("Title updated!")
                .build()
        );
    }
}
