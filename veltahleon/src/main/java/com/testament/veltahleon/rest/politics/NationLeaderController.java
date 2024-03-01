package com.testament.veltahleon.rest.politics;

import com.testament.veltahleon.model.entities.politics.NationLeader;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.politics.NationLeaderService;
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
@RequestMapping("/politics")
@RequiredArgsConstructor
public class NationLeaderController {

    @Autowired
    private NationLeaderService nationLeaderService;

    @GetMapping("/nationLeaders/all")
    public ResponseEntity<CustomResponse> getAllNationLeaders() {
        List<NationLeader> nationLeaders = (List<NationLeader>) nationLeaderService.getNationLeaders();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("allNationLeaders", nationLeaders))
                .message("All nation leaders retrieved!")
                .build()
        );
    }

    @GetMapping("/nationLeader/{id}")
    public ResponseEntity<CustomResponse> getNationLeaderByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedNationLeaderByID", nationLeaderService.getNationLeaderByID(id)))
                .message("Nation leader retrieved!")
                .build()
        );
    }

    @GetMapping(value = "/nationLeader/image/{fileName}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public byte[] getPersonImage(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get("src/main/java/com/testament/veltahleon/assets/images/people/" + fileName));
    }

    @PostMapping("/save/nationLeader")
    public ResponseEntity<CustomResponse> saveNationLeader(@RequestBody @Valid NationLeader nationLeader) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("savedNationLeader", nationLeaderService.saveNationLeader(nationLeader)))
                .message("Nation leader saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/nationLeader/{id}")
    public ResponseEntity<CustomResponse> deleteNationLeaderByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("isNationLeaderDeleted", nationLeaderService.deleteNationLeaderByID(id)))
                .message("Nation leader deleted!")
                .build()
        );
    }

    @PatchMapping("/update/nationLeader/{id}")
    public ResponseEntity<CustomResponse> updateNationLeader(@PathVariable Long id, @RequestBody @Valid NationLeader nationLeader) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("updatedNationLeader", nationLeaderService.updateNationLeader(id, nationLeader)))
                .message("Nation leader updated!")
                .build()
        );
    }
}
