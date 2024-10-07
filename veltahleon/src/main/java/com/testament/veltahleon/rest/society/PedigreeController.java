package com.testament.veltahleon.rest.society;

import com.testament.veltahleon.model.entities.society.Pedigree;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.society.PedigreeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/society")
@RequiredArgsConstructor
public class PedigreeController {

    @Autowired
    private PedigreeService pedigreeService;

    @GetMapping("/pedigrees/all")
    public ResponseEntity<CustomResponse> getAllPedigrees() {
        List<Pedigree> pedigrees = (List<Pedigree>) pedigreeService.getPedigrees();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", pedigrees))
                .message("All pedigrees retrieved!")
                .build()
        );
    }

    @GetMapping("/pedigree/{id}")
    public ResponseEntity<CustomResponse> getPedigreeByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", pedigreeService.getPedigreeByID(id)))
                .message("Pedigree retrieved!")
                .build()
        );
    }

    @PostMapping("/save/pedigree")
    public ResponseEntity<CustomResponse> savePedigree(@RequestBody @Valid Pedigree pedigree) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", pedigreeService.savePedigree(pedigree)))
                .message("Pedigree saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/pedigree/{id}")
    public ResponseEntity<CustomResponse> deletePedigreeByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", pedigreeService.deletePedigreeByID(id)))
                .message("Pedigree deleted!")
                .build()
        );
    }

    @PatchMapping("/update/pedigree/{id}")
    public ResponseEntity<CustomResponse> updatePedigree(@PathVariable Long id, @RequestBody @Valid Pedigree pedigree) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", pedigreeService.updatePedigree(id, pedigree)))
                .message("Pedigree updated!")
                .build()
        );
    }

    @PutMapping("/modify/pedigree/{id}")
    public ResponseEntity<CustomResponse> modifyPedigree(@PathVariable Long id, @RequestBody @Valid Pedigree pedigree) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", pedigreeService.modifyPedigree(id, pedigree)))
                .message("Pedigree updated!")
                .build()
        );
    }
}
