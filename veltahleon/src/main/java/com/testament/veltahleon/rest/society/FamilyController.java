package com.testament.veltahleon.rest.society;

import com.testament.veltahleon.model.entities.society.Family;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.society.FamilyService;
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
public class FamilyController {

    @Autowired
    private FamilyService familyService;

    @GetMapping("/families")
    public ResponseEntity<CustomResponse> getPaginatedFamilies(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Family> families = (List<Family>) familyService.getFamiliesWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("paginatedFamilies", families))
                .message(families.size() + " families retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/families/all")
    public ResponseEntity<CustomResponse> getAllFamilies() {
        List<Family> families = (List<Family>) familyService.getFamilies();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("allFamilies", families))
                .message("All families retrieved!")
                .build()
        );
    }

    @GetMapping("/family/{id}")
    public ResponseEntity<CustomResponse> getFamilyByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedFamilyByID", familyService.getFamilyByID(id)))
                .message("Family retrieved!")
                .build()
        );
    }

    @GetMapping("/family/person")
    public ResponseEntity<CustomResponse> getFamilyByPersonName(@RequestParam(value = "personName") String personName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedFamilyByName", familyService.getFamilyByPersonName(personName)))
                .message("Family retrieved!")
                .build()
        );
    }

    @GetMapping(value = "/family/image/{fileName}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public byte[] getFamilyCoatOfArms(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get("src/main/java/com/testament/veltahleon/assets/images/coat_of_arms/" + fileName));
    }

    @PostMapping("/save/family")
    public ResponseEntity<CustomResponse> saveFamily(@RequestBody @Valid Family family) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("savedFamily", familyService.saveFamily(family)))
                .message("Family saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/family/{id}")
    public ResponseEntity<CustomResponse> deleteFamilyByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("isFamilyDeleted", familyService.deleteFamilyByID(id)))
                .message("Family deleted!")
                .build()
        );
    }

    @PatchMapping("/update/family/{id}")
    public ResponseEntity<CustomResponse> updateFamily(@PathVariable Long id, @RequestBody Family family) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("updatedFamily", familyService.updateFamily(id, family)))
                .message("Family updated!")
                .build()
        );
    }
}
