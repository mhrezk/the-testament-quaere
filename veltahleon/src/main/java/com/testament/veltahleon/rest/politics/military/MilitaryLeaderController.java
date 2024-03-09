package com.testament.veltahleon.rest.politics.military;

import com.testament.veltahleon.model.entities.politics.military.MilitaryLeader;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.politics.military.MilitaryLeaderService;
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
@RequestMapping("/politics/military")
@RequiredArgsConstructor
public class MilitaryLeaderController {

    @Autowired
    private MilitaryLeaderService militaryLeaderService;

    @GetMapping("/militaryLeaders")
    public ResponseEntity<CustomResponse> getPaginatedMilitaryLeaders(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<MilitaryLeader> militaryLeaders = (List<MilitaryLeader>) militaryLeaderService.getMilitaryLeadersWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", militaryLeaders))
                .message(militaryLeaders.size() + " military leaders retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/militaryLeaders/all")
    public ResponseEntity<CustomResponse> getAllMilitaryLeaders() {
        List<MilitaryLeader> militaryLeaders = (List<MilitaryLeader>) militaryLeaderService.getMilitaryLeaders();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", militaryLeaders))
                .message("All military leaders retrieved!")
                .build()
        );
    }

    @GetMapping("/militaryLeader/{id}")
    public ResponseEntity<CustomResponse> getMilitaryLeaderByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", militaryLeaderService.getMilitaryLeaderByID(id)))
                .message("Military leader retrieved!")
                .build()
        );
    }

    @GetMapping("/militaryLeader/name")
    public ResponseEntity<CustomResponse> getMilitaryLeaderByMilitaryLeaderName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", militaryLeaderService.getMilitaryLeaderByName(name)))
                .message("Military leader retrieved!")
                .build()
        );
    }

    @GetMapping(value = "/militaryLeader/image/{fileName}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public byte[] getPersonImage(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get("src/main/java/com/testament/veltahleon/assets/images/people/" + fileName));
    }

    @PostMapping("/save/militaryLeader")
    public ResponseEntity<CustomResponse> saveMilitaryLeader(@RequestBody @Valid MilitaryLeader militaryLeader) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", militaryLeaderService.saveMilitaryLeader(militaryLeader)))
                .message("Military leader saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/militaryLeader/{id}")
    public ResponseEntity<CustomResponse> deleteMilitaryLeaderByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", militaryLeaderService.deleteMilitaryLeaderByID(id)))
                .message("Military leader deleted!")
                .build()
        );
    }

    @PatchMapping("/update/militaryLeader/{id}")
    public ResponseEntity<CustomResponse> updateMilitaryLeader(@PathVariable Long id, @RequestBody @Valid MilitaryLeader militaryLeader) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", militaryLeaderService.updateMilitaryLeader(id, militaryLeader)))
                .message("Military leader updated!")
                .build()
        );
    }
}
