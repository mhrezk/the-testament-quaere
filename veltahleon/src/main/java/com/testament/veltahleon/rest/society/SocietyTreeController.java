package com.testament.veltahleon.rest.society;

import com.testament.veltahleon.model.entities.society.SocietyTree;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.society.SocietyTreeService;
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
public class SocietyTreeController {

    @Autowired
    private SocietyTreeService societyTreeService;

    @GetMapping("/societyTrees")
    public ResponseEntity<CustomResponse> getPaginatedSocietyTrees(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<SocietyTree> SocietyTrees = (List<SocietyTree>) societyTreeService.getSocietyTreesWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", SocietyTrees))
                .message(SocietyTrees.size() + " societyTrees retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/societyTrees/all")
    public ResponseEntity<CustomResponse> getAllSocietyTrees() {
        List<SocietyTree> SocietyTrees = (List<SocietyTree>) societyTreeService.getSocietyTrees();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", SocietyTrees))
                .message("All societyTrees retrieved!")
                .build()
        );
    }

    @GetMapping("/societyTree/{id}")
    public ResponseEntity<CustomResponse> getSocietyTreeByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", societyTreeService.getSocietyTreeByID(id)))
                .message("SocietyTree retrieved!")
                .build()
        );
    }

//    @GetMapping("/SocietyTree/person")
//    public ResponseEntity<CustomResponse> getSocietyTreeByPersonName(@RequestParam(value = "personName") String personName) {
//        return ResponseEntity.ok(CustomResponse.builder()
//                .timestamp(LocalDateTime.now())
//                .status(HttpStatus.OK)
//                .statusCode(HttpStatus.OK.value())
//                .data(Map.of("datumRetrieved", SocietyTreeService.getSocietyTreeByPersonName(personName)))
//                .message("SocietyTree retrieved!")
//                .build()
//        );
//    }

    @GetMapping("/societyTree/treeName")
    public ResponseEntity<CustomResponse> getSocietyTreeBySocietyTreeName(@RequestParam(value = "societyTreeName") String societyTreeName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", societyTreeService.getSocietyTreeByLineageName(societyTreeName)))
                .message("SocietyTree retrieved!")
                .build()
        );
    }

//    @GetMapping(value = "/SocietyTree/image/{fileName}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE})
//    public byte[] getSocietyTreeCoatOfArms(@PathVariable("fileName") String fileName) throws IOException {
//        return Files.readAllBytes(Paths.get("src/main/java/com/testament/veltahleon/assets/images/coat_of_arms/" + fileName));
//    }

    @PostMapping("/save/societyTree")
    public ResponseEntity<CustomResponse> saveSocietyTree(@RequestBody @Valid SocietyTree societyTree) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", societyTreeService.saveSocietyTree(societyTree)))
                .message("SocietyTree saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/societyTree/{id}")
    public ResponseEntity<CustomResponse> deleteSocietyTreeByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", societyTreeService.deleteSocietyTreeByID(id)))
                .message("SocietyTree deleted!")
                .build()
        );
    }

    @PatchMapping("/update/societyTree/{id}")
    public ResponseEntity<CustomResponse> updateSocietyTree(@PathVariable Long id, @RequestBody SocietyTree societyTree) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", societyTreeService.updateSocietyTree(id, societyTree)))
                .message("SocietyTree updated!")
                .build()
        );
    }

    @PutMapping("/modify/societyTree/{id}")
    public ResponseEntity<CustomResponse> modifySocietyTree(@PathVariable Long id, @RequestBody SocietyTree societyTree) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", societyTreeService.modifySocietyTree(id, societyTree)))
                .message("SocietyTree updated!")
                .build()
        );
    }
}
