package com.testament.veltahleon.rest.politics.military;

import com.testament.veltahleon.model.entities.politics.military.SquadLeader;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.politics.military.SquadLeaderService;
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

@RestController
@RequestMapping("/politics/military")
@RequiredArgsConstructor
public class SquadLeaderController {

    @Autowired
    private SquadLeaderService squadLeaderService;

    @GetMapping("/squadLeaders")
    public ResponseEntity<CustomResponse> getPaginatedSquadLeaders(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<SquadLeader> squadLeaders = (List<SquadLeader>) squadLeaderService.getSquadLeadersWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("paginatedSquadLeaders", squadLeaders))
                .message(squadLeaders.size() + " squad leaders retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/squadLeaders/all")
    public ResponseEntity<CustomResponse> getAllSquadLeaders() {
        List<SquadLeader> squadLeaders = (List<SquadLeader>) squadLeaderService.getSquadLeaders();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("allSquadLeaders", squadLeaders))
                .message("All squad leaders retrieved!")
                .build()
        );
    }

    @GetMapping("/squadLeader/{id}")
    public ResponseEntity<CustomResponse> getSquadLeaderByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedSquadLeaderByID", squadLeaderService.getSquadLeaderByID(id)))
                .message("Squad leader retrieved!")
                .build()
        );
    }

    @GetMapping("/squadLeader/name")
    public ResponseEntity<CustomResponse> getSquadLeaderBySquadLeaderName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedSquadLeaderByName", squadLeaderService.getSquadLeaderByName(name)))
                .message("Squad leader retrieved!")
                .build()
        );
    }

    @GetMapping(value = "/people/image/{fileName}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public byte[] getPersonImage(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get("src/main/java/com/testament/veltahleon/assets/images/people/" + fileName));
    }

    @PostMapping("/save/squadLeader")
    public ResponseEntity<CustomResponse> saveSquadLeader(@RequestBody @Valid SquadLeader squadLeader) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("savedSquadLeader", squadLeaderService.saveSquadLeader(squadLeader)))
                .message("Squad leader saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/squadLeader/{id}")
    public ResponseEntity<CustomResponse> deleteSquadLeaderByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("isSquadLeaderDeleted", squadLeaderService.deleteSquadLeaderByID(id)))
                .message("Squad leader deleted!")
                .build()
        );
    }

    @PatchMapping("/update/squadLeader/{id}")
    public ResponseEntity<CustomResponse> updateSquadLeader(@PathVariable Long id, @RequestBody @Valid SquadLeader squadLeader) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("updatedSquadLeader", squadLeaderService.updateSquadLeader(id, squadLeader)))
                .message("Squad leader updated!")
                .build()
        );
    }
}
